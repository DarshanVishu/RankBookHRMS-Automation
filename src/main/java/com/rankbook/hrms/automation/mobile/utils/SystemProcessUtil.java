package com.rankbook.hrms.automation.mobile.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class SystemProcessUtil {
    private SystemProcessUtil() {}

    public static String execute(String command) {
        StringBuilder output = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }
            process.waitFor();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
        return output.toString().trim();
    }
}