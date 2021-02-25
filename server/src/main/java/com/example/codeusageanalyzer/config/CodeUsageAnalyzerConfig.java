package com.example.codeusageanalyzer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.codeusageanalyzer.jooq.tables.daos.JCalleeDao;
import com.example.codeusageanalyzer.jooq.tables.daos.JClassDao;
import com.example.codeusageanalyzer.jooq.tables.daos.JDependencyDao;
import com.example.codeusageanalyzer.jooq.tables.daos.JInterfaceDao;
import com.example.codeusageanalyzer.jooq.tables.daos.JMethodDao;
import com.example.codeusageanalyzer.jooq.tables.daos.JModuleDao;
import com.example.codeusageanalyzer.jooq.tables.daos.JRepositoryDao;

@Configuration
public class CodeUsageAnalyzerConfig {

    @Bean
    public JCalleeDao jCalleeDao(org.jooq.Configuration jooqConfiguration) {
        return new JCalleeDao(jooqConfiguration);
    }

    @Bean
    public JClassDao jClassDao(org.jooq.Configuration jooqConfiguration) {
        return new JClassDao(jooqConfiguration);
    }

    @Bean
    public JDependencyDao jDependencyDao(org.jooq.Configuration jooqConfiguration) {
        return new JDependencyDao(jooqConfiguration);
    }

    @Bean
    public JInterfaceDao jInterfaceDao(org.jooq.Configuration jooqConfiguration) {
        return new JInterfaceDao(jooqConfiguration);
    }

    @Bean
    public JMethodDao jMethodDao(org.jooq.Configuration jooqConfiguration) {
        return new JMethodDao(jooqConfiguration);
    }

    @Bean
    public JModuleDao jModuleDao(org.jooq.Configuration jooqConfiguration) {
        return new JModuleDao(jooqConfiguration);
    }

    @Bean
    public JRepositoryDao jRepositoryDao(org.jooq.Configuration jooqConfiguration) {
        return new JRepositoryDao(jooqConfiguration);
    }
}
