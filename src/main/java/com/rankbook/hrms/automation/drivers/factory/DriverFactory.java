package com.rankbook.hrms.automation.drivers.factory;

import com.rankbook.hrms.automation.core.exceptions.TargetEngineNotSupportedException;
import com.rankbook.hrms.automation.drivers.capabilities.AppiumCapabilitiesManager;
import com.rankbook.hrms.automation.drivers.capabilities.ChromeOptionsManager;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URI;

public final class DriverFactory {
    public static WebDriver createWebDriver(TargetEngine engine) {
        if (engine == TargetEngine.SELENIUM_CHROME) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(ChromeOptionsManager.getOptions());
        }
        throw new TargetEngineNotSupportedException("Unsupported web engine: " + engine);
    }

    public static AndroidDriver createAndroidDriver(String appiumServerUrl, String deviceName) {
        try {
            return new AndroidDriver(
                    URI.create(appiumServerUrl).toURL(),
                    AppiumCapabilitiesManager.getUiAutomator2Options(deviceName)
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to establish Appium AndroidDriver session", e);
        }
    }
}