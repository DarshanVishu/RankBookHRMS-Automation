package com.rankbook.hrms.automation.tests.mobile;

import com.rankbook.hrms.automation.core.constants.FrameworkConstants;
import com.rankbook.hrms.automation.mobile.pages.MobileTapInOutScreen;
import com.rankbook.hrms.automation.mobile.utils.GeoMockingUtil;
import com.rankbook.hrms.automation.tests.base.BaseMobileTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GeofencePunchInTests extends BaseMobileTest {

    private MobileTapInOutScreen tapScreen;

    @BeforeClass(alwaysRun = true, dependsOnMethods = "setUp")
    public void setupTestClassState() {
        tapScreen = new MobileTapInOutScreen(driver);

        // 1. Inject Mysore Office coordinates
        GeoMockingUtil.setLocation(
            driver,
            FrameworkConstants.MYSORE_OFFICE_LATITUDE,
            FrameworkConstants.MYSORE_OFFICE_LONGITUDE,
            FrameworkConstants.DEFAULT_ALTITUDE
        );

        // 2. Ensure initial state is clean (resets only if previously punched in)
        tapScreen.ensurePunchedOutState();
    }

    @Test(priority = 1, description = "Validate punch-in flow with valid Mysore office GPS coordinates")
    public void testSuccessfulPunchInWithMockLocation() {
        Assert.assertFalse(
            tapScreen.isGeofenceUnconfiguredErrorDisplayed(),
            "Error banner should not be visible when coordinates are valid."
        );

        // Execute punch in
        tapScreen.tapPunchIn();

        // Assert UI updates to Punched In state
        Assert.assertTrue(
            tapScreen.isCurrentlyPunchedIn(),
            "App state should update to 'Currently punched in'."
        );
        Assert.assertTrue(
            tapScreen.isTimeAtWorkVisible(),
            "'Time at Work' or Session info should be actively displayed."
        );
    }

    @Test(priority = 2, dependsOnMethods = {"testSuccessfulPunchInWithMockLocation"}, description = "Validate punch-out flow")
    public void testSuccessfulPunchOut() {
        // Execute punch out
        tapScreen.tapPunchOut();

        // Assert user has successfully punched out
        Assert.assertFalse(
            tapScreen.isCurrentlyPunchedIn(),
            "User should no longer be punched in."
        );
    }
}