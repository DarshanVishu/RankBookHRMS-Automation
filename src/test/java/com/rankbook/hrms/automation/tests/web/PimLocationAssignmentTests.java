package com.rankbook.hrms.automation.tests.web;

import com.rankbook.hrms.automation.tests.base.BaseWebTest;
import com.rankbook.hrms.automation.web.selenium.pages.WebLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class PimLocationAssignmentTests extends BaseWebTest {

    @Test(description = "Verify PIM Employee navigation loads cleanly")
    public void testPimEmployeeListLoads() {
        // BaseWebTest has already opened the base URL
        WebLoginPage loginPage = new WebLoginPage(driver);
        loginPage.loginWithCredentials("care@rankbook", "b9FHkByDQru2RSA#");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 1. Wait until authentication finishes
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/auth/login")));

        // 2. Wait until background loaders dismiss
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".oxd-loading-spinner, .oxd-form-loader")));

        // 3. Locate and click PIM menu item
        By pimLocator = By.cssSelector("a[href*='viewPimModule']");
        WebElement pimMenu = wait.until(ExpectedConditions.presenceOfElementLocated(pimLocator));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(pimLocator)).click();
        } catch (Exception e) {
            // Fallback for headless click interception
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", pimMenu);
        }

        // 4. Assert header renders cleanly
        WebElement pimHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//header//h6[contains(., 'PIM')] | //h6[normalize-space()='PIM']")
        ));

        Assert.assertTrue(pimHeader.isDisplayed(), "PIM page header is not visible.");
    }
}