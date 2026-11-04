package com.rankbook.hrms.automation.tests.mobile;

import com.rankbook.hrms.automation.tests.base.BaseMobileTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RemoteWfhBypassTests extends BaseMobileTest {

    @Test(description = "Verify remote work banner confirms geofencing bypass")
    public void testWfhGeofenceBypassBanner() {
        boolean isBannerDisplayed = driver.findElement(
            By.xpath("//*[contains(@text, 'Remote / Work From Home') or contains(@text, 'Geofencing bypassed')]")
        ).isDisplayed();

        Assert.assertTrue(isBannerDisplayed, "Remote/WFH geofencing bypass badge is not displayed.");
    }
}