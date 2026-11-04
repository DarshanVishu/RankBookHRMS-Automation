package com.rankbook.hrms.automation.mobile.pages;

import com.rankbook.hrms.automation.web.common.BaseMobilePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class MobileHistoryScreen extends BaseMobilePage {

    private final By historyTab = AppiumBy.xpath("//*[@text='History']");
    private final By historyItemList = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView//*[contains(@text, 'Session')]");

    public MobileHistoryScreen(AndroidDriver driver) {
        super(driver);
    }

    public void openHistoryTab() {
        waitForClickable(historyTab).click();
    }

    public boolean hasHistoryRecords() {
        try {
            return waitForVisible(historyItemList).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}