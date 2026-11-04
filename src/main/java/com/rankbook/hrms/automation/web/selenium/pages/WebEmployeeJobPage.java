package com.rankbook.hrms.automation.web.selenium.pages;

import com.rankbook.hrms.automation.web.common.BaseMobilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebEmployeeJobPage extends BaseMobilePage {

    private final By jobTab = By.xpath("//a[contains(text(), 'Job')]");
    private final By locationDropdown = By.xpath("//label[text()='Location']/../following-sibling::div//div[contains(@class, 'oxd-select-text')]");
    private final By saveButton = By.xpath("//button[@type='submit' and contains(., 'Save')]");

    public WebEmployeeJobPage(WebDriver driver) {
        super(driver);
    }

    public void assignLocation(String locationName) {
        waitForClickable(jobTab).click();
        waitForClickable(locationDropdown).click();
        By option = By.xpath("//div[@role='listbox']//*[contains(text(), '" + locationName + "')]");
        waitForClickable(option).click();
        waitForClickable(saveButton).click();
    }
}