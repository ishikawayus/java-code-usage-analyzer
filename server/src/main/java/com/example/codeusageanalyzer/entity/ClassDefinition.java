package com.example.codeusageanalyzer.entity;

import java.nio.file.Path;
import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ClassDefinition {

    private final Path path;

    private final String name;

    private final String superName;

    private final List<String> interfaces;

    private final List<MethodDefinition> methodDefinitions;
}
