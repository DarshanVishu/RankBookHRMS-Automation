package com.rankbook.hrms.automation.tests.base;

import com.rankbook.hrms.automation.drivers.managers.AppiumDriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseMobileTest {

    protected AndroidDriver driver;

    @BeforeClass(alwaysRun = true)
    @Parameters({"deviceName"})
    public void setUp(@Optional("Pixel 8 API 37.2") String deviceName) {
        AppiumDriverManager.initDriver(deviceName);
        this.driver = AppiumDriverManager.getDriver();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        AppiumDriverManager.quitDriver();
    }
}