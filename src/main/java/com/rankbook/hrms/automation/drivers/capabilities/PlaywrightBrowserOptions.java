package com.rankbook.hrms.automation.drivers.capabilities;

import com.microsoft.playwright.BrowserType;

public final class PlaywrightBrowserOptions {
    public static BrowserType.LaunchOptions getLaunchOptions() {
        return new BrowserType.LaunchOptions()
                .setHeadless(Boolean.parseBoolean(System.getProperty("headless", "false")))
                .setSlowMo(50);
    }
}