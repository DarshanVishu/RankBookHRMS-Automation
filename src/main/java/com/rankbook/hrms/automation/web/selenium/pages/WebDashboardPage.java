package com.rankbook.hrms.automation.web.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDashboardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Matches the breadcrumb or standard h6 header
    private final By dashboardHeader = By.xpath(
        "//h6[translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='dashboard'] " +
        "| //span[contains(@class, 'oxd-topbar-header-breadcrumb')]//h6"
    );
    private final By loadingSpinner = By.cssSelector(".oxd-loading-spinner, .oxd-form-loader");

    public WebDashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * Matches the exact method call in WebLoginTests.java
     */
    public boolean isDashboardHeaderDisplayed() {
        try {
            // 1. Wait until authentication redirects away from login
            wait.until(d -> !this.driver.getCurrentUrl().contains("/auth/login"));

            // 2. Wait until global overlays/loaders are gone
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));

            // 3. Verify header visibility
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Alias for backwards compatibility
     */
    public boolean isDashboardDisplayed() {
        return isDashboardHeaderDisplayed();
    }

    public String getHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).getText();
    }

    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}