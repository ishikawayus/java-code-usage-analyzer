package com.example.codeusageanalyzer.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class FileUtil {

    public static void delete(Path dir) {
        try (Stream<Path> stream = Files.walk(dir, FileVisitOption.FOLLOW_LINKS)) {
            stream.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            dir.toFile().delete();
        } catch (IOException e) {
            throw new FileUtilException("Failed to delete: dir=" + dir);
        }
    }

    @SuppressWarnings("serial")
    public static class FileUtilException extends RuntimeException {

        public FileUtilException(String message) {
            super(message);
        }
    }
}
