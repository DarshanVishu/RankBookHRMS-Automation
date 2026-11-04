package com.rankbook.hrms.automation.web.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebLoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Standard OrangeHRM 5.x locators with fallbacks
    private final By usernameField = By.xpath("//input[@name='username' or contains(@placeholder, 'Username')]");
    private final By passwordField = By.xpath("//input[@name='password' or @type='password']");
    private final By loginButton   = By.xpath("//button[@type='submit']");

    public WebLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void login(String username, String password) {
        loginWithCredentials(username, password);
    }

    public void loginWithCredentials(String username, String password) {
        // Wait for page hydration and username visibility
        WebElement userElem = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        userElem.clear();
        userElem.sendKeys(username);

        WebElement passElem = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passElem.clear();
        passElem.sendKeys(password);

        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        submitBtn.click();
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}