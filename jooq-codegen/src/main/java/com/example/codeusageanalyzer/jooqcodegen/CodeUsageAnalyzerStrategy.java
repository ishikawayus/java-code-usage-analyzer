package com.example.codeusageanalyzer.jooqcodegen;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

public class CodeUsageAnalyzerStrategy extends DefaultGeneratorStrategy {

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        String name = super.getJavaClassName(definition, mode);
        if (mode == Mode.POJO) {
            return "J" + name + "Pojo";
        } else {
            return "J" + name;
        }
    }
}
