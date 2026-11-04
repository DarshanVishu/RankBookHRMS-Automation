package com.rankbook.hrms.automation.tests.web;

import com.rankbook.hrms.automation.tests.base.BaseWebTest;
import com.rankbook.hrms.automation.web.selenium.pages.WebDashboardPage;
import com.rankbook.hrms.automation.web.selenium.pages.WebLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebLoginTests extends BaseWebTest {

    @Test(description = "Verify successful admin login loads the dashboard")
    public void testAdminLoginSuccess() {
        WebLoginPage loginPage = new WebLoginPage(driver);
        
        // Ensure credentials match your active portal (care@rankbook or Admin)
        loginPage.loginWithCredentials("care@rankbook", "b9FHkByDQru2RSA#");

        WebDashboardPage dashboardPage = new WebDashboardPage(driver);
        boolean isDisplayed = dashboardPage.isDashboardHeaderDisplayed();

        Assert.assertTrue(isDisplayed, "Dashboard header was not displayed after login within timeout.");
    }
}