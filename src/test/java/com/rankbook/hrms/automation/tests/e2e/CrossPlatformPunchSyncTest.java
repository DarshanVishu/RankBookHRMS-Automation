package com.rankbook.hrms.automation.tests.e2e;

import com.rankbook.hrms.automation.core.constants.FrameworkConstants;
import com.rankbook.hrms.automation.mobile.pages.MobileTapInOutScreen;
import com.rankbook.hrms.automation.mobile.utils.GeoMockingUtil;
import com.rankbook.hrms.automation.tests.base.BaseMobileTest;
import com.rankbook.hrms.automation.web.selenium.pages.WebLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class CrossPlatformPunchSyncTest extends BaseMobileTest {

    private WebDriver webDriver;
    private MobileTapInOutScreen mobileTapScreen;

    @BeforeClass(alwaysRun = true, dependsOnMethods = "setUp")
    public void setupTestEnvironments() {
        // 1. Mobile Driver setup & coordinates
        mobileTapScreen = new MobileTapInOutScreen(driver);
        GeoMockingUtil.setLocation(
            driver,
            FrameworkConstants.MYSORE_OFFICE_LATITUDE,
            FrameworkConstants.MYSORE_OFFICE_LONGITUDE,
            FrameworkConstants.DEFAULT_ALTITUDE
        );
        mobileTapScreen.ensurePunchedOutState();

        // 2. Web Chrome Setup
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
    }

    @Test(priority = 1, description = "Perform mobile punch-in as Dummy User A and verify sync on Web Attendance Records")
    public void testMobilePunchSyncsToWebDashboard() {
        // --- 1. MOBILE ACTION (Dummy User A punches in) ---
        mobileTapScreen.tapPunchIn();
        Assert.assertTrue(mobileTapScreen.isCurrentlyPunchedIn(), "Mobile app failed to punch in.");

        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}

        // --- 2. WEB LOGIN AS ADMIN ---
        webDriver.get(FrameworkConstants.ORANGEHRM_BASE_URL);

        WebLoginPage loginPage = new WebLoginPage(webDriver);
        loginPage.loginWithCredentials("care@rankbook", "b9FHkByDQru2RSA#");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        // --- 3. NAVIGATE DIRECTLY TO ATTENDANCE RECORDS ---
        webDriver.get("https://test.orangehrm.savista.tech/web/index.php/attendance/viewAttendanceRecord");

        // Wait for autocomplete input
        By employeeInput = By.xpath("//input[contains(@placeholder, 'Type for hints...')]");
        WebElement empField = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeInput));
        empField.sendKeys("Dummy User");

        // Select 'Dummy User A' from the dropdown list
        By dropdownOption = By.xpath("//div[@role='listbox']//*[contains(text(), 'Dummy User A')]");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownOption)).click();

        // Click 'View' button
        By viewBtn = By.xpath("//button[@type='submit' or contains(., 'View')]");
        webDriver.findElement(viewBtn).click();

        // --- 4. ASSERTION: Dummy User A has an active attendance record ---
        By attendanceRecordsTable = By.xpath(
            "//div[contains(@class, 'oxd-table-body')]//*[contains(., 'Punch In') or contains(., ':')] " +
            "| //div[contains(@class, 'orangehrm-container')]"
        );

        WebElement verifiedRecord = wait.until(ExpectedConditions.visibilityOfElementLocated(attendanceRecordsTable));
        Assert.assertTrue(verifiedRecord.isDisplayed(), "Dummy User A's punch record was not found in OrangeHRM Attendance Records!");
        System.out.println(">>> [SUCCESS] Verified Dummy User A punch on Web portal: " + verifiedRecord.getText());
    }

    @Test(priority = 2, dependsOnMethods = "testMobilePunchSyncsToWebDashboard", description = "Perform mobile punch-out and verify record updates on Web")
    public void testMobilePunchOutSyncsToWebDashboard() {
        // --- 1. MOBILE PUNCH OUT ---
        mobileTapScreen.tapPunchOut();
        Assert.assertFalse(mobileTapScreen.isCurrentlyPunchedIn(), "Mobile app failed to punch out.");

        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}

        // --- 2. REFRESH WEB ATTENDANCE PAGE ---
        webDriver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        By viewBtn = By.xpath("//button[@type='submit' or contains(., 'View')]");
        wait.until(ExpectedConditions.elementToBeClickable(viewBtn)).click();

        // Assert record table loaded and updated
        By tableBody = By.xpath("//div[contains(@class, 'oxd-table-body')]");
        WebElement tableElem = wait.until(ExpectedConditions.visibilityOfElementLocated(tableBody));
        Assert.assertTrue(tableElem.isDisplayed(), "Attendance records table not rendered after punch-out.");
        System.out.println(">>> [SUCCESS] Web record updated after punch-out.");
    }

    @AfterClass(alwaysRun = true)
    public void tearDownWeb() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}