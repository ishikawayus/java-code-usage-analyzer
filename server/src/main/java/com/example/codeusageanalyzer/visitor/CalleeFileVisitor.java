package com.example.codeusageanalyzer.visitor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.objectweb.asm.ClassReader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.example.codeusageanalyzer.entity.ClassDefinition;
import com.example.codeusageanalyzer.entity.Module;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public class CalleeFileVisitor extends SimpleFileVisitor<Path> {

    private final String dependenciesFileName;

    private final Map<Path, Module> moduleByPath = new HashMap<>();

    private final List<ClassDefinition> classDefinitions = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Objects.requireNonNull(file);
        Objects.requireNonNull(attrs);
        if (file.getFileName().toString().equals(dependenciesFileName)) {
            readDependenciesFile(file);
        }
        if (file.getFileName().toString().endsWith(".class")) {
            readClassFile(file);
        }
        return FileVisitResult.CONTINUE;
    }

    private void readClassFile(Path path) throws IOException {
        try (InputStream is = Files.newInputStream(path)) {
            readClassFile(path, is);
        }
    }

    private void readDependenciesFile(Path path) {
        Path pom = path.getParent().getParent().resolve("pom.xml");
        if (!pom.toFile().exists()) {
            return;
        }
        Document document;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pom.toFile());
        } catch (SAXException | IOException | ParserConfigurationException e) {
            log.error("Failed to parse pom.xml: path={}", pom, e);
            return;
        }
        String groupId = null;
        String artifactId = null;
        String version = null;

        Node projectNode = document.getFirstChild();
        if (projectNode == null || !"project".equals(projectNode.getNodeName())) {
            log.error("Failed to parse pom.xml: path={}", pom);
            return;
        }
        for (int i = 0; i < projectNode.getChildNodes().getLength(); i++) {
            Node node = projectNode.getChildNodes().item(i);
            if ("parent".equals(node.getLocalName())) {
                for (int j = 0; j < projectNode.getChildNodes().getLength(); j++) {
                    Node node2 = node.getChildNodes().item(i);
                    if ("groupId".equals(node2.getNodeName()) && groupId == null) {
                        groupId = node2.getTextContent();
                    }
                    if ("artifactId".equals(node2.getNodeName()) && artifactId == null) {
                        artifactId = node2.getTextContent();
                    }
                    if ("version".equals(node2.getNodeName()) && version == null) {
                        version = node2.getTextContent();
                    }
                }
            }
            if ("groupId".equals(node.getNodeName())) {
                groupId = node.getTextContent();
            }
            if ("artifactId".equals(node.getNodeName())) {
                artifactId = node.getTextContent();
            }
            if ("version".equals(node.getNodeName())) {
                version = node.getTextContent();
            }
        }
        if (groupId == null || artifactId == null || version == null) {
            log.error("Failed to parse pom.xml: path={}", pom);
            return;
        }

        List<Module> dependencies = new ArrayList<>();
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            lines.forEach(line -> {
                // line => groupId:artifactId:classifier:version:scope
                String[] parts = line.trim().split(":");
                if (parts.length == 5) {
                    dependencies.add(Module.builder().groupId(parts[0]).artifactId(parts[1]).version(parts[3]).build());
                }
            });
        } catch (IOException e) {
            log.error("Failed to parse dependencies: path={}", path, e);
            return;
        }

        moduleByPath.put(path.getParent(), Module.builder().groupId(groupId).artifactId(artifactId).version(version)
                .dependencies(dependencies).build());
    }

    private void readClassFile(Path path, InputStream is) throws IOException {
        CalleeClassVisitor classVisitor = new CalleeClassVisitor();
        new ClassReader(is).accept(classVisitor, 0);
        classDefinitions.add(ClassDefinition.builder().path(path).name(classVisitor.getName())
                .superName(classVisitor.getSuperName()).interfaces(classVisitor.getInterfaces())
                .methodDefinitions(classVisitor.getMethodDefinitions()).build());
    }
}
