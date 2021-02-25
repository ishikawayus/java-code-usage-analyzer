package com.example.codeusageanalyzer.entity;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MethodDefinition {

    private final String name;

    private final String descriptor;

    private final List<CalleeMethod> calleeMethods;
}
