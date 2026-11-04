package com.rankbook.hrms.automation.web.common;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseMobilePage {
    protected AndroidDriver driver;
    protected WebDriverWait wait;

    public BaseMobilePage(WebDriver driver2) {
        this.driver = (AndroidDriver) driver2;
        this.wait = new WebDriverWait(driver2, Duration.ofSeconds(15));
    }

    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}