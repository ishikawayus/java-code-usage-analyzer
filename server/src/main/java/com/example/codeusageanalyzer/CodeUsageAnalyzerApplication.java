package com.example.codeusageanalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.codeusageanalyzer.entity.CalleeMethod;
import com.example.codeusageanalyzer.entity.CalleeResult;
import com.example.codeusageanalyzer.entity.ClassDefinition;
import com.example.codeusageanalyzer.entity.MethodDefinition;
import com.example.codeusageanalyzer.entity.Module;
import com.example.codeusageanalyzer.jooq.tables.JCallee;
import com.example.codeusageanalyzer.jooq.tables.JClass;
import com.example.codeusageanalyzer.jooq.tables.JMethod;
import com.example.codeusageanalyzer.jooq.tables.JModule;
import com.example.codeusageanalyzer.jooq.tables.JRepository;
import com.example.codeusageanalyzer.jooq.tables.daos.JCalleeDao;
import com.example.codeusageanalyzer.jooq.tables.daos.JClassDao;
import com.example.codeusageanalyzer.jooq.tables.daos.JDependencyDao;
import com.example.codeusageanalyzer.jooq.tables.daos.JInterfaceDao;
import com.example.codeusageanalyzer.jooq.tables.daos.JMethodDao;
import com.example.codeusageanalyzer.jooq.tables.daos.JModuleDao;
import com.example.codeusageanalyzer.jooq.tables.pojos.JCalleePojo;
import com.example.codeusageanalyzer.jooq.tables.pojos.JClassPojo;
import com.example.codeusageanalyzer.jooq.tables.pojos.JDependencyPojo;
import com.example.codeusageanalyzer.jooq.tables.pojos.JInterfacePojo;
import com.example.codeusageanalyzer.jooq.tables.pojos.JMethodPojo;
import com.example.codeusageanalyzer.jooq.tables.pojos.JModulePojo;
import com.example.codeusageanalyzer.jooq.tables.records.JRepositoryRecord;
import com.example.codeusageanalyzer.util.FileUtil;
import com.example.codeusageanalyzer.util.ProcessUtil;
import com.example.codeusageanalyzer.visitor.CalleeFileVisitor;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class CodeUsageAnalyzerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CodeUsageAnalyzerApplication.class, args);
        context.getBean(CodeUsageAnalyzerApplication.class).run();
    }

    private final DSLContext dsl;

    private final JCalleeDao jCalleeDao;

    private final JClassDao jClassDao;

    private final JDependencyDao jDependencyDao;

    private final JInterfaceDao jInterfaceDao;

    private final JMethodDao jMethodDao;

    private final JModuleDao jModuleDao;

    private void run() {
        Result<JRepositoryRecord> repositories = dsl.selectFrom(JRepository.REPOSITORY)
                .orderBy(JRepository.REPOSITORY.REPOSITORY_ID).fetch();
        if (repositories.isEmpty()) {
            log.info("repositories is empty");
            return;
        }
        for (JRepositoryRecord repository : repositories) {
            String repositoryId = repository.getRepositoryId();
            String url = repository.getUrl();
            if (dsl.fetchExists(dsl.selectFrom(JModule.MODULE).where(JModule.MODULE.REPOSITORY_ID.eq(repositoryId)))) {
                log.info("Exists: repositoryId={}, url={}", repositoryId, url);
                continue;
            }
            log.info("Deleting the previous repository");
            FileUtil.delete(Paths.get("./repo"));
            log.info("Analyzing: git clone: url={}", url);
            ProcessUtil.gitClone(url, "repo", new File("."));
            log.info("Analyzing: mvn compile");
            ProcessUtil.mvnCompile("dependencies.txt", new File("./repo"));
            log.info("Analyzing: analyze");
            analyze(repositoryId);
        }
        log.info("Finished");

        List<CalleeResult> result = dsl
                .select(JRepository.REPOSITORY.URL, JModule.MODULE.PATH, JClass.CLASS.CLASS_NAME,
                        JMethod.METHOD.METHOD_NAME, JMethod.METHOD.DESCRIPTOR, JCallee.CALLEE.OWNER_NAME,
                        JCallee.CALLEE.METHOD_NAME, JCallee.CALLEE.DESCRIPTOR)
                .from(JCallee.CALLEE).join(JMethod.METHOD).on(JMethod.METHOD.METHOD_ID.eq(JCallee.CALLEE.METHOD_ID))
                .join(JClass.CLASS).on(JClass.CLASS.CLASS_ID.eq(JMethod.METHOD.CLASS_ID)).join(JModule.MODULE)
                .on(JModule.MODULE.MODULE_ID.eq(JClass.CLASS.MODULE_ID)).join(JRepository.REPOSITORY)
                .on(JRepository.REPOSITORY.REPOSITORY_ID.eq(JModule.MODULE.REPOSITORY_ID)).fetch().stream()
                .map(r -> CalleeResult.builder().url(r.get(JRepository.REPOSITORY.URL)).path(r.get(JModule.MODULE.PATH))
                        .className(r.get(JClass.CLASS.CLASS_NAME)).methodName(r.get(JMethod.METHOD.METHOD_NAME))
                        .descriptor(r.get(JMethod.METHOD.DESCRIPTOR)).calleeOwnerName(r.get(JCallee.CALLEE.OWNER_NAME))
                        .calleeMethodName(r.get(JCallee.CALLEE.METHOD_NAME))
                        .calleeDescriptor(r.get(JCallee.CALLEE.DESCRIPTOR)).build())
                .collect(Collectors.toList());
        try {
            CsvMapper csvMapper = new CsvMapper();
            csvMapper.writer(csvMapper.schemaFor(CalleeResult.class).withHeader()).writeValue(new File("result.csv"),
                    result);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write result", e);
        }
        log.info("Wrote result");
    }

    private void analyze(String repositoryId) {
        CalleeFileVisitor fileVisitor = new CalleeFileVisitor("dependencies.txt");
        try {
            Files.walkFileTree(Paths.get("./repo"), fileVisitor);
        } catch (IOException e) {
            log.error("Failed to walk: repositoryId=" + repositoryId, e);
            return;
        }

        Map<Path, String> moduleIdByPath = new HashMap<>();

        List<JModulePojo> newModules = new ArrayList<>();
        List<JDependencyPojo> newDependencies = new ArrayList<>();
        for (Entry<Path, Module> entry : fileVisitor.getModuleByPath().entrySet()) {
            Path path = entry.getKey();
            Module module = entry.getValue();
            JModulePojo newModule = createModule(repositoryId, path, module);
            moduleIdByPath.put(path, newModule.getModuleId());
            newModules.add(newModule);

            for (Module dependency : module.getDependencies()) {
                JDependencyPojo newDependency = createDependency(newModule.getModuleId(), dependency);
                newDependencies.add(newDependency);
            }
        }

        List<JClassPojo> newClasses = new ArrayList<>();
        List<JInterfacePojo> newInterfaces = new ArrayList<>();
        List<JMethodPojo> newMethods = new ArrayList<>();
        List<JCalleePojo> newCallees = new ArrayList<>();
        for (ClassDefinition classDefinition : fileVisitor.getClassDefinitions()) {
            String moduleId = findModuleId(moduleIdByPath, classDefinition.getPath());
            if (moduleId == null) {
                log.error("Failed to get moduleId: repositoryId={}, classDefinition={}", repositoryId, classDefinition);
            }
            JClassPojo newClass = createClass(moduleId, classDefinition);
            newClasses.add(newClass);
            for (String interfaceName : classDefinition.getInterfaces()) {
                JInterfacePojo newInterface = createInterface(newClass.getClassId(), interfaceName);
                newInterfaces.add(newInterface);
            }
            for (MethodDefinition methodDefinition : classDefinition.getMethodDefinitions()) {
                JMethodPojo newMethod = createMethod(newClass.getClassId(), methodDefinition);
                newMethods.add(newMethod);
                for (CalleeMethod calleeMethod : methodDefinition.getCalleeMethods()) {
                    JCalleePojo newCallee = createCallee(newMethod.getMethodId(), calleeMethod);
                    newCallees.add(newCallee);
                }
            }
        }

        jCalleeDao.insert(newCallees);
        jMethodDao.insert(newMethods);
        jInterfaceDao.insert(newInterfaces);
        jClassDao.insert(newClasses);
        jDependencyDao.insert(newDependencies);
        jModuleDao.insert(newModules);
    }

    private static String findModuleId(Map<Path, String> moduleIdByPath, Path path) {
        return moduleIdByPath.entrySet().stream()
                .filter(entry -> path.toAbsolutePath().startsWith(entry.getKey().toAbsolutePath())).map(Entry::getValue)
                .findFirst().orElse(null);
    }

    private static JModulePojo createModule(String repositoryId, Path path, Module module) {
        JModulePojo newModule = new JModulePojo();
        newModule.setModuleId(UUID.randomUUID().toString());
        newModule.setRepositoryId(repositoryId);
        newModule.setPath(Paths.get("./repo").relativize(path.getParent()).toString());
        newModule.setGroupId(module.getGroupId());
        newModule.setArtifactId(module.getArtifactId());
        newModule.setVersion(module.getVersion());
        return newModule;
    }

    private static JDependencyPojo createDependency(String moduleId, Module dependency) {
        JDependencyPojo newDependency = new JDependencyPojo();
        newDependency.setDependencyId(UUID.randomUUID().toString());
        newDependency.setModuleId(moduleId);
        newDependency.setGroupId(dependency.getGroupId());
        newDependency.setArtifactId(dependency.getArtifactId());
        newDependency.setVersion(dependency.getVersion());
        return newDependency;
    }

    private static JClassPojo createClass(String moduleId, ClassDefinition classDefinition) {
        JClassPojo newClass = new JClassPojo();
        newClass.setClassId(UUID.randomUUID().toString());
        newClass.setModuleId(moduleId);
        newClass.setClassName(classDefinition.getName());
        newClass.setSuperClassName(classDefinition.getSuperName());
        return newClass;
    }

    private static JInterfacePojo createInterface(String classId, String interfaceName) {
        JInterfacePojo newInterface = new JInterfacePojo();
        newInterface.setInterfaceId(UUID.randomUUID().toString());
        newInterface.setClassId(classId);
        newInterface.setInterfaceName(interfaceName);
        return newInterface;
    }

    private static JMethodPojo createMethod(String classId, MethodDefinition methodDefinition) {
        JMethodPojo newMethod = new JMethodPojo();
        newMethod.setMethodId(UUID.randomUUID().toString());
        newMethod.setClassId(classId);
        newMethod.setMethodName(methodDefinition.getName());
        newMethod.setDescriptor(methodDefinition.getDescriptor());
        return newMethod;
    }

    private static JCalleePojo createCallee(String methodId, CalleeMethod calleeMethod) {
        JCalleePojo newCallee = new JCalleePojo();
        newCallee.setCalleeId(UUID.randomUUID().toString());
        newCallee.setMethodId(methodId);
        newCallee.setOwnerName(calleeMethod.getOwner());
        newCallee.setMethodName(calleeMethod.getName());
        newCallee.setDescriptor(calleeMethod.getDescriptor());
        newCallee.setInvokeDynamic(calleeMethod.isInvokeDynamic());
        return newCallee;
    }
}
