package com.example.codeusageanalyzer.entity;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Module {

    private final String groupId;

    private final String artifactId;

    private final String version;

    private final List<Module> dependencies;
}
