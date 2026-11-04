package com.rankbook.hrms.automation.tests.base;

import com.rankbook.hrms.automation.core.constants.FrameworkConstants;
import com.rankbook.hrms.automation.drivers.managers.AppiumDriverManager;
import com.rankbook.hrms.automation.drivers.managers.SeleniumDriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseHybridTest {

    protected AndroidDriver mobileDriver;
    protected WebDriver webDriver;

    @BeforeClass
    @Parameters({"deviceName"})
    public void setUpEngines(@Optional("Pixel 8 API 37.2") String deviceName) {
        // Initialize Appium (Android Emulator)
        AppiumDriverManager.initDriver(deviceName);
        this.mobileDriver = AppiumDriverManager.getDriver();

        // Initialize Selenium (Chrome Web Dashboard)
        SeleniumDriverManager.initDriver();
        this.webDriver = SeleniumDriverManager.getDriver();
        this.webDriver.get(FrameworkConstants.ORANGEHRM_BASE_URL);
    }

    @AfterClass(alwaysRun = true)
    public void tearDownEngines() {
        AppiumDriverManager.quitDriver();
        SeleniumDriverManager.quitDriver();
    }
}