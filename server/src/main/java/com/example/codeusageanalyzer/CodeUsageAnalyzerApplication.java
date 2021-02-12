package com.example.codeusageanalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.codeusageanalyzer.entity.ClassDefinition;
import com.example.codeusageanalyzer.jooq.tables.JClass;
import com.example.codeusageanalyzer.jooq.tables.JRepository;
import com.example.codeusageanalyzer.jooq.tables.daos.JClassDao;
import com.example.codeusageanalyzer.jooq.tables.pojos.JClassPojo;
import com.example.codeusageanalyzer.jooq.tables.records.JRepositoryRecord;
import com.example.codeusageanalyzer.util.FileUtil;
import com.example.codeusageanalyzer.util.ProcessUtil;
import com.example.codeusageanalyzer.visitor.CalleeFileVisitor;

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

    private final JClassDao jClassDao;

    private void run() {
        log.info("Hello, World");
        Result<JRepositoryRecord> repositories = dsl.selectFrom(JRepository.REPOSITORY)
                .orderBy(JRepository.REPOSITORY.REPOSITORY_ID).fetch();
        if (repositories.isEmpty()) {
            log.info("repositories is empty");
            return;
        }
        for (JRepositoryRecord repository : repositories) {
            long repositoryId = repository.getRepositoryId();
            String url = repository.getUrl();
            ProcessUtil.gitClone(url, "repo", new File("."));
            ProcessUtil.mvnCompile(new File("./repo"));
            analyze(repositoryId);
            FileUtil.delete(Paths.get("./repo"));
        }
    }

    private void analyze(long repositoryId) {
        CalleeFileVisitor fileVisitor = new CalleeFileVisitor();
        try {
            Files.walkFileTree(Paths.get("./repo"), fileVisitor);
        } catch (IOException e) {
            log.error("Failed to walk: repositoryId=" + repositoryId, e);
            return;
        }
        List<ClassDefinition> classDefinitions = fileVisitor.getClassDefinitions();

        dsl.deleteFrom(JClass.CLASS).where(JClass.CLASS.MODULE_ID.eq(repositoryId));

        jClassDao.insert(classDefinitions.stream().map(classDefinition -> {
            JClassPojo pojo = new JClassPojo();
            pojo.setModuleId(repositoryId);
            pojo.setPath(classDefinition.getPath().toString());
            pojo.setClassName(classDefinition.getName());
            pojo.setSuperClassName(classDefinition.getSuperName());
            return pojo;
        }).collect(Collectors.toList()));
    }
}
