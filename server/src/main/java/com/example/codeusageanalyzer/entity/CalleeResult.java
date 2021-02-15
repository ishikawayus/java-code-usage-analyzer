package com.example.codeusageanalyzer.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CalleeResult {

    private String url;

    private String path;

    private String className;

    private String methodName;

    private String descriptor;

    private String calleeOwnerName;

    private String calleeMethodName;

    private String calleeDescriptor;
}
