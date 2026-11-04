package com.rankbook.hrms.automation.drivers.capabilities;

import io.appium.java_client.android.options.UiAutomator2Options;

import java.time.Duration;

public final class AppiumCapabilitiesManager {
    public static UiAutomator2Options getUiAutomator2Options(String deviceName) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName(deviceName != null ? deviceName : "Pixel 8 API 37.2");
        options.setAppPackage("com.rankbook.hrms");
        options.setAppActivity("com.rankbook.hrms.MainActivity");
        options.setNoReset(true);
        options.setFullReset(false);
        options.setAutoGrantPermissions(true);
        options.setNewCommandTimeout(Duration.ofSeconds(120));
        return options;
    }
}