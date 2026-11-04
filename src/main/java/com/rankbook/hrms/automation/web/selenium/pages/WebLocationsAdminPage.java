package com.rankbook.hrms.automation.web.selenium.pages;

import com.rankbook.hrms.automation.web.common.BaseMobilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebLocationsAdminPage extends BaseMobilePage {

    private final By addLocationBtn = By.xpath("//button[contains(., 'Add')]");
    private final By nameField = By.xpath("//label[text()='Name']/../following-sibling::div/input");
    private final By cityField = By.xpath("//label[text()='City']/../following-sibling::div/input");
    private final By saveBtn = By.xpath("//button[@type='submit']");

    public WebLocationsAdminPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLocations() {
        driver.get("https://test.orangehrm.savista.tech/web/index.php/admin/viewLocations");
    }

    public void createLocation(String name, String city) {
        waitForClickable(addLocationBtn).click();
        waitForVisible(nameField).sendKeys(name);
        waitForVisible(cityField).sendKeys(city);
        waitForClickable(saveBtn).click();
    }
}