package com.rankbook.hrms.automation.drivers.capabilities;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;

public final class PlaywrightBrowserOptions {

    public static BrowserType.LaunchOptions getLaunchOptions() {
        return new BrowserType.LaunchOptions()
                .setHeadless(Boolean.parseBoolean(System.getProperty("headless", "false")))
                .setSlowMo(50);
    }

    public static Browser.NewContextOptions getDesktopContextOptions() {
        return new Browser.NewContextOptions()
                .setViewportSize(1920, 1080);
    }

    public static Browser.NewContextOptions getIosSafariContextOptions() {
        return new Browser.NewContextOptions()
                .setViewportSize(393, 852) // iPhone 15 Pro dimensions
                .setDeviceScaleFactor(3)
                .setIsMobile(true)
                .setHasTouch(true)
                .setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 17_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.5 Mobile/15E148 Safari/604.1");
    }
}