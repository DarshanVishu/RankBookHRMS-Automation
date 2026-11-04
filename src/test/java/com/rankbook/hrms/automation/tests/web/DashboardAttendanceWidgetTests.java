package com.rankbook.hrms.automation.tests.web;

import com.rankbook.hrms.automation.core.constants.FrameworkConstants;
import com.rankbook.hrms.automation.tests.base.BaseWebTest;
import com.rankbook.hrms.automation.web.selenium.pages.WebLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class DashboardAttendanceWidgetTests extends BaseWebTest {

    @Test(description = "Verify Time at Work dashboard widget is visible upon login")
    public void testAttendanceWidgetIsRendered() {
        driver.get(FrameworkConstants.ORANGEHRM_BASE_URL);
        WebLoginPage loginPage = new WebLoginPage(driver);
        loginPage.loginWithCredentials("care@rankbook", "b9FHkByDQru2RSA#");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        boolean widgetPresent = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Time at Work']"))
        ).isDisplayed();

        Assert.assertTrue(widgetPresent, "Time at Work widget not visible on Admin dashboard.");
    }
}