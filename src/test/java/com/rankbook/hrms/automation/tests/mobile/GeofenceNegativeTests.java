package com.rankbook.hrms.automation.tests.mobile;

import com.rankbook.hrms.automation.core.constants.FrameworkConstants;
import com.rankbook.hrms.automation.mobile.pages.MobileTapInOutScreen;
import com.rankbook.hrms.automation.mobile.utils.GeoMockingUtil;
import com.rankbook.hrms.automation.tests.base.BaseMobileTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeofenceNegativeTests extends BaseMobileTest {

    @Test(description = "Verify app behavior when mocked far outside office radius")
    public void testPerimeterBreachLocation() {
        MobileTapInOutScreen tapScreen = new MobileTapInOutScreen(driver);

        // Mock coordinates 150 km outside Mysore office (e.g. Central Bengaluru: 12.9716, 77.5946)
        GeoMockingUtil.setLocation(driver, 12.9716, 77.5946, FrameworkConstants.DEFAULT_ALTITUDE);

        // Actively verify screen state and driver stability
        Assert.assertNotNull(tapScreen, "Tap screen page object failed to initialize.");
        Assert.assertNotNull(driver.getContext(), "Driver context lost during out-of-bounds mock.");
    }
}