package com.rankbook.hrms.automation.web.selenium.pages;

import com.rankbook.hrms.automation.web.common.BaseMobilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WebAttendancePage extends BaseMobilePage {

    private final By employeeInput = By.xpath("//input[contains(@placeholder, 'Type for hints...')]");
    private final By viewButton = By.xpath("//button[@type='submit' or contains(., 'View')]");
    private final By recordsTable = By.xpath("//div[contains(@class, 'oxd-table-body')]");

    public WebAttendancePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToRecords() {
        driver.get("https://test.orangehrm.savista.tech/web/index.php/attendance/viewAttendanceRecord");
    }

    public void searchEmployeeAttendance(String employeeName) {
        WebElement empField = waitForVisible(employeeInput);
        empField.sendKeys(employeeName);
        By dropdownOption = By.xpath("//div[@role='listbox']//*[contains(text(), '" + employeeName + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownOption)).click();
        waitForClickable(viewButton).click();
    }

    public boolean isRecordTableRendered() {
        return waitForVisible(recordsTable).isDisplayed();
    }
}