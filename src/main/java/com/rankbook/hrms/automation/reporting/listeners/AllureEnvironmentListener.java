package com.rankbook.hrms.automation.reporting.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class AllureEnvironmentListener implements ISuiteListener {

    @Override
    public void onFinish(ISuite suite) {
        Path resultsDir = Paths.get("target/allure-results");
        try {
            if (!Files.exists(resultsDir)) {
                Files.createDirectories(resultsDir);
            }

            Properties props = new Properties();
            props.setProperty("Application", "OrangeHRM 5.x");
            props.setProperty("Environment", "Staging / Localhost");
            props.setProperty("Framework", "TestNG + Selenium + Appium + RestAssured");
            props.setProperty("Java Version", System.getProperty("java.version"));
            props.setProperty("OS", System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ")");
            props.setProperty("User", System.getProperty("user.name"));
            props.setProperty("Appium Port", "4723");
            props.setProperty("Suite Name", suite.getName());

            try (FileOutputStream fos = new FileOutputStream(resultsDir.resolve("environment.properties").toFile())) {
                props.store(fos, "Allure Environment Specifications");
            }
        } catch (IOException e) {
            System.err.println("Failed to write Allure environment.properties: " + e.getMessage());
        }
    }
}