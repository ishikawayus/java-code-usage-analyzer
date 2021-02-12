package com.example.codeusageanalyzer;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import org.jooq.DSLContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.codeusageanalyzer.jooq.tables.JRepository;
import com.example.codeusageanalyzer.util.FileUtil;
import com.example.codeusageanalyzer.util.ProcessUtil;

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

        List<String> urls = dsl.selectDistinct(JRepository.REPOSITORY.URL).from(JRepository.REPOSITORY)
                .orderBy(JRepository.REPOSITORY.URL).fetch().getValues(JRepository.REPOSITORY.URL);
        if (urls.isEmpty()) {
            log.info("repository url is empty");
            return;
        }
        for (String url : urls) {
            ProcessUtil.gitClone(url, "repo", new File("."));
            ProcessUtil.mvnCompile(new File("./repo"));
            FileUtil.delete(Paths.get("./repo"));
        }
    }
}
