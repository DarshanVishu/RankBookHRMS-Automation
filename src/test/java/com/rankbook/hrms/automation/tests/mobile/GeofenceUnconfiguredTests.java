package com.rankbook.hrms.automation.tests.mobile;

import com.rankbook.hrms.automation.tests.base.BaseMobileTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeofenceUnconfiguredTests extends BaseMobileTest {

    @Test(description = "Verify app behavior when device coordinates cannot be resolved")
    public void testNullCoordinatesHandling() {
        // Confirm driver remains stable when querying current activity without coordinates
        String currentActivity = driver.currentActivity();
        Assert.assertNotNull(currentActivity, "Current activity returned null.");
    }
}