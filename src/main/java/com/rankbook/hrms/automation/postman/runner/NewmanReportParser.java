package com.rankbook.hrms.automation.postman.runner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class NewmanReportParser {
    private final ObjectMapper mapper = new ObjectMapper();

    public boolean hasFailures(File newmanJsonReport) {
        try {
            JsonNode root = mapper.readTree(newmanJsonReport);
            JsonNode failures = root.at("/run/failures");
            return failures.isArray() && !failures.isEmpty();
        } catch (Exception e) {
            return true;
        }
    }

    public int getTotalTests(File newmanJsonReport) {
        try {
            JsonNode root = mapper.readTree(newmanJsonReport);
            return root.at("/run/stats/tests/total").asInt(0);
        } catch (Exception e) {
            return 0;
        }
    }
}