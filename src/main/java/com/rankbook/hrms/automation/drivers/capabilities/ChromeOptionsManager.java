package com.rankbook.hrms.automation.drivers.capabilities;

import org.openqa.selenium.chrome.ChromeOptions;

public final class ChromeOptionsManager {

    private ChromeOptionsManager() {
        // Prevent direct instantiation
    }

    public static ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();

        // Check if headless mode is triggered via CLI or CI/CD (-Dheadless=true)
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        if (isHeadless) {
            options.addArguments("--headless=new");
            // Mandatory flags for Linux CI runners and containerized agents
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }

        // General browser stability flags
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");

        return options;
    }
}