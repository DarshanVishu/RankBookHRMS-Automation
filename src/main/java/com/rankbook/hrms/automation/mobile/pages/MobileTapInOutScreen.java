package com.rankbook.hrms.automation.mobile.pages;

import com.rankbook.hrms.automation.mobile.utils.GeoMockingUtil;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class MobileTapInOutScreen {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // Case-insensitive Jetpack Compose Locators
    private final By punchInText = AppiumBy.xpath(
            "//*[contains(translate(@text, 'punch in', 'PUNCH IN'), 'PUNCH IN')]"
    );
    private final By punchOutText = AppiumBy.xpath(
            "//*[contains(translate(@text, 'punch out', 'PUNCH OUT'), 'PUNCH OUT')]"
    );
    private final By punchedInStatusText = AppiumBy.xpath(
            "//*[contains(translate(@text, 'currently punched in', 'CURRENTLY PUNCHED IN'), 'CURRENTLY PUNCHED IN')]"
    );
    private final By assignedStatusText = AppiumBy.xpath(
            "//*[contains(@text, 'Remote') or contains(@text, 'Office') or contains(@text, 'Geofencing')]"
    );
    private final By unconfiguredError = AppiumBy.xpath(
            "//*[contains(@text, 'unconfigured') or contains(@text, 'not allowed')]"
    );
    private final By sessionInfoElement = AppiumBy.xpath(
            "//*[contains(@text, 'Last Session') or contains(@text, 'Time at Work') or contains(@text, 'Record arrival')]"
    );

    public MobileTapInOutScreen(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void mockDeviceCoordinates(double latitude, double longitude) {
        GeoMockingUtil.setEmulatorLocationViaAdb(longitude, latitude);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
    }

    public void ensurePunchedOutState() {
        try {
            // If already punched in or punch out card is visible, tap it to reset
            List<WebElement> punchOutElements = driver.findElements(punchOutText);
            if (!punchOutElements.isEmpty() && punchOutElements.get(0).isDisplayed()) {
                tapElementCenter(punchOutElements.get(0));
                handleConfirmationDialog();
                Thread.sleep(2000);
            }
        } catch (Exception ignored) {}
    }

    public boolean isGeofenceUnconfiguredErrorDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(unconfiguredError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void tapElementCenter(WebElement element) {
        int centerX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        int centerY = element.getLocation().getY() + (element.getSize().getHeight() / 2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(tap));
    }

    private void handleConfirmationDialog() {
        try {
            WebDriverWait dialogWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement confirmBtn = dialogWait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.xpath("//*[contains(@text, 'OK') or contains(@text, 'Done') or contains(@text, 'CONFIRM')]")
            ));
            confirmBtn.click();
        } catch (Exception ignored) {}
    }

    public void tapPunchIn() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(punchInText));
        tapElementCenter(button);
        handleConfirmationDialog();
    }

    public void tapPunchOut() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(punchOutText));
        tapElementCenter(button);
        handleConfirmationDialog();
    }

    public boolean isCurrentlyPunchedIn() {
        WebDriverWait stateWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return stateWait.until(d -> {
                boolean hasPunchedInLabel = !d.findElements(punchedInStatusText).isEmpty();
                boolean hasPunchOutAction = !d.findElements(punchOutText).isEmpty();
                return hasPunchedInLabel || hasPunchOutAction;
            });
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTimeAtWorkVisible() {
        try {
            return !driver.findElements(sessionInfoElement).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public String getAssignedOffice() {
        try {
            WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(assignedStatusText));
            return status.getText().trim();
        } catch (Exception e) {
            return "null";
        }
    }
}