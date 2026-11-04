package com.rankbook.hrms.automation.core.config;

import com.rankbook.hrms.automation.core.exceptions.FrameworkInitializationException;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new FrameworkInitializationException("Unable to locate config.properties in src/test/resources");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new FrameworkInitializationException("Failed to load configuration properties", e);
        }
    }

    public static String get(String key) {
        return System.getProperty(key, properties.getProperty(key));
    }

    public static String getOrDefault(String key, String defaultValue) {
        return System.getProperty(key, properties.getProperty(key, defaultValue));
    }
}