package com.example.codeusageanalyzer.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CalleeMethod {

    private final String owner;

    private final String name;

    private final String descriptor;

    private final boolean isInvokeDynamic;
}
