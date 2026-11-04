package com.rankbook.hrms.automation.drivers.managers;

import com.rankbook.hrms.automation.core.constants.FrameworkConstants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URI;
import java.time.Duration;

public final class AppiumDriverManager {

    private static final ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<>();

    private AppiumDriverManager() {}

    public static AndroidDriver getDriver() {
        return DRIVER.get();
    }

    // Overload 1: Default device
    public static void initDriver() {
        initDriver("Pixel 8 API 37.2");
    }

    // Overload 2: With custom deviceName parameter
    public static synchronized void initDriver(String deviceName) {
        if (DRIVER.get() == null) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setAutomationName("UiAutomator2");
            options.setDeviceName(deviceName != null ? deviceName : "Pixel 8 API 37.2");
            options.setAppPackage(FrameworkConstants.APP_PACKAGE);
            options.setAppActivity(FrameworkConstants.APP_ACTIVITY);
            
            // Allow splash/intermediate redirects without timing out
            options.setAppWaitActivity("*");
            options.setNoReset(true);
            options.setAutoGrantPermissions(true);

            // Stability timeouts to prevent Appium from killing the session prematurely
            options.setNewCommandTimeout(Duration.ofSeconds(300));
            options.setAdbExecTimeout(Duration.ofSeconds(60));
            options.setUiautomator2ServerLaunchTimeout(Duration.ofSeconds(60));
            options.setUiautomator2ServerInstallTimeout(Duration.ofSeconds(60));

            try {
                URI serverUri = URI.create(FrameworkConstants.APPIUM_SERVER_URL);
                AndroidDriver driver = new AndroidDriver(serverUri.toURL(), options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                DRIVER.set(driver);
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize AndroidDriver at: " 
                        + FrameworkConstants.APPIUM_SERVER_URL + " - " + e.getMessage(), e);
            }
        }
    }

    public static synchronized void quitDriver() {
        AndroidDriver driver = DRIVER.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {
            } finally {
                DRIVER.remove();
            }
        }
    }
}