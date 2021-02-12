package com.example.codeusageanalyzer;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.codeusageanalyzer.jooq.tables.JRepository;

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

    private void run() {
        log.info("Hello, World");

        Result<Record> result = dsl.select().from(JRepository.REPOSITORY).fetch();
        log.info("result={}", result);
    }
}
