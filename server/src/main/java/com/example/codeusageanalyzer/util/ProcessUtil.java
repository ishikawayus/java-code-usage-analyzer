package com.example.codeusageanalyzer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcessUtil {

    public static void gitClone(String url, String dir, File workdir) {
        List<String> command = Arrays.asList("git", "clone", "--depth=1", url, dir);
        execute(command, workdir);
    }

    public static void mvnCompile(File workdir) {
        List<String> command = Arrays.asList("mvn", "compile", "--batch-mode", "-Dskip.npm");
        execute(command, workdir);
    }

    private static void execute(List<String> command, File workdir) {
        if (isWindows()) {
            command = new ArrayList<>(command);
            command.addAll(0, Arrays.asList("cmd", "/c"));
        }
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(workdir);
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            String string;
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), "Shift-JIS"))) {
                while ( (string = reader.readLine()) != null) {
                    System.out.println(string);
                }
            }
            int code = process.waitFor();
            if (code != 0) {
                throw new ProcessUtilException("Failed to execute: command=" + command + ", workdir=" + workdir);
            }
        } catch (InterruptedException | IOException e) {
            throw new ProcessUtilException("Failed to execute: command=" + command + ", workdir=" + workdir, e);
        }
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().startsWith("windows");
    }

    @SuppressWarnings("serial")
    public static class ProcessUtilException extends RuntimeException {

        public ProcessUtilException(String message) {
            super(message);
        }

        public ProcessUtilException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
