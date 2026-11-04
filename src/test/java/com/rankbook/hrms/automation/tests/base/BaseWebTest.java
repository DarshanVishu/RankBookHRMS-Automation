package com.rankbook.hrms.automation.tests.base;

import com.rankbook.hrms.automation.core.constants.FrameworkConstants;
import com.rankbook.hrms.automation.drivers.capabilities.ChromeOptionsManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseWebTest {
    protected WebDriver driver;

    // Default to your framework's primary HRMS URL
    protected String baseUrl = System.getProperty("baseUrl", FrameworkConstants.ORANGEHRM_BASE_URL);

    @BeforeMethod(alwaysRun = true)
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(ChromeOptionsManager.getOptions());

        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        if (isHeadless) {
            // Force standard 1080p viewport in headless mode to keep side panels expanded
            driver.manage().window().setSize(new Dimension(1920, 1080));
        } else {
            driver.manage().window().maximize();
        }

        driver.get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}