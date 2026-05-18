package com.medicalstore.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.*;
import java.util.*;

@Component
public class FileUtil {

    // Resolved at runtime: <project-root>/<data.dir>/
    // user.dir is set by the JVM to the directory from which it was launched,
    // which for Spring Boot (run via Maven or IDE from project root) is the project root.
    @Value("${user.dir}")
    private String projectDir;

    @Value("${data.dir:data}")
    private String dataDir;

    private String getDataDir() {
        return projectDir + File.separator + dataDir + File.separator;
    }

    // Make sure the data folder and file exist
    private Path ensureFile(String filename) throws IOException {
        Path dir = Paths.get(getDataDir());
        if (!Files.exists(dir)) Files.createDirectories(dir);
        Path file = dir.resolve(filename);
        if (!Files.exists(file)) Files.createFile(file);
        return file;
    }

    // Read all lines from a file (skips empty lines)
    public List<String> readAll(String filename) {
        try {
            Path path = ensureFile(filename);
            List<String> lines = new ArrayList<>();
            for (String line : Files.readAllLines(path)) {
                if (!line.trim().isEmpty()) lines.add(line.trim());
            }
            return lines;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    // Append a new line to the file
    public boolean append(String filename, String line) {
        try {
            Path path = ensureFile(filename);
            Files.writeString(path, line + System.lineSeparator(),
                    StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Rewrite the entire file with a new list of lines
    public boolean writeAll(String filename, List<String> lines) {
        try {
            Path path = ensureFile(filename);
            Files.write(path, lines);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Generate a simple unique ID: prefix + timestamp
    public String generateId(String prefix) {
        return prefix + System.currentTimeMillis();
    }
}
