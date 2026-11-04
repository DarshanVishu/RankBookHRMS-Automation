package com.rankbook.hrms.automation.mobile.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rankbook.hrms.automation.core.exceptions.FrameworkInitializationException;

import java.io.InputStream;

public final class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtil() {}

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            throw new FrameworkInitializationException("JSON parse error: " + e.getMessage(), e);
        }
    }

    public static <T> T fromResource(String resourcePath, Class<T> clazz) {
        try (InputStream stream = JsonUtil.class.getClassLoader().getResourceAsStream(resourcePath)) {
            return MAPPER.readValue(stream, clazz);
        } catch (Exception e) {
            throw new FrameworkInitializationException("Failed to read JSON resource: " + resourcePath, e);
        }
    }

    public static String toJson(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new FrameworkInitializationException("JSON serialization error: " + e.getMessage(), e);
        }
    }
}