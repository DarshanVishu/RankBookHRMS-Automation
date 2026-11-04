package com.rankbook.hrms.automation.postman.runner;

import com.rankbook.hrms.automation.core.exceptions.FrameworkInitializationException;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NewmanRunner {

    private final String collectionPath;
    private String environmentPath;
    private String reportOutputDir;
    private String reportFileName;

    public NewmanRunner(String collectionPath) {
        this.collectionPath = collectionPath;
        this.reportOutputDir = "target/newman-reports";
        this.reportFileName = "newman-report-" + System.currentTimeMillis();
    }

    public NewmanRunner withEnvironment(String environmentPath) {
        this.environmentPath = environmentPath;
        return this;
    }

    public NewmanRunner withReportOutput(String outputDir, String fileName) {
        this.reportOutputDir = outputDir;
        this.reportFileName = fileName;
        return this;
    }

    /**
     * Executes the Newman collection CLI command and returns exit code (0 = success).
     */
    public int run() {
        try {
            // Ensure output directory exists
            Path outputPath = Paths.get(reportOutputDir);
            if (!Files.exists(outputPath)) {
                Files.createDirectories(outputPath);
            }

            File jsonReportFile = new File(reportOutputDir, reportFileName + ".json");

            // Build Command: newman run <collection> -e <env> -r cli,json --reporter-json-export <path>
            List<String> command = new ArrayList<>();
            
            boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");
            command.add(isWindows ? "newman.cmd" : "newman");
            command.add("run");
            command.add(collectionPath);

            if (environmentPath != null && !environmentPath.isEmpty()) {
                command.add("-e");
                command.add(environmentPath);
            }

            command.add("--reporters");
            command.add("cli,json");
            command.add("--reporter-json-export");
            command.add(jsonReportFile.getAbsolutePath());

            System.out.println(">>> Executing Newman Command: " + String.join(" ", command));

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            // Stream console output
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[Newman] " + line);
                }
            }

            int exitCode = process.waitFor();
            System.out.println(">>> Newman run completed with exit code: " + exitCode);
            return exitCode;

        } catch (Exception e) {
            throw new FrameworkInitializationException("Failed to execute Newman runner for collection: " + collectionPath, e);
        }
    }

    public File getGeneratedJsonReport() {
        return new File(reportOutputDir, reportFileName + ".json");
    }
}