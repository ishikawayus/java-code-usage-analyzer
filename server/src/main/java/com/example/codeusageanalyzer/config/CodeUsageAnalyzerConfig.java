package com.example.codeusageanalyzer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.codeusageanalyzer.jooq.tables.daos.JClassDao;

@Configuration
public class CodeUsageAnalyzerConfig {

    @Bean
    public JClassDao jClassDao(org.jooq.Configuration jooqConfiguration) {
        return new JClassDao(jooqConfiguration);
    }
}
