package com.rankbook.hrms.automation.tests.mobile;

import com.rankbook.hrms.automation.tests.base.BaseMobileTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileAuthenticationTests extends BaseMobileTest {

    @Test(description = "Verify mobile app displays currently logged-in user identity")
    public void testUserIdentityDisplayed() {
        boolean isUserGreetingVisible = driver.findElement(
            By.xpath("//*[contains(@text, 'Hello') or contains(@text, 'Dummy')]")
        ).isDisplayed();

        Assert.assertTrue(isUserGreetingVisible, "User identity greeting not visible on mobile home screen.");
    }
}