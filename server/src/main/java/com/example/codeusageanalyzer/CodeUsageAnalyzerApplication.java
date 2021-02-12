package com.example.codeusageanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class CodeUsageAnalyzerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CodeUsageAnalyzerApplication.class, args);
        context.getBean(CodeUsageAnalyzerApplication.class).run();
    }

    private void run() {
        log.info("Hello, World");
    }
}
