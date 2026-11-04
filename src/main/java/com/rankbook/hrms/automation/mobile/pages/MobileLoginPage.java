package com.rankbook.hrms.automation.mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import com.rankbook.hrms.automation.web.common.BaseMobilePage;

public class MobileLoginPage extends BaseMobilePage {

    private final By usernameField = AppiumBy.xpath("//android.widget.EditText[contains(@text, 'Username') or @resource-id='login_username']");
    private final By passwordField = AppiumBy.xpath("//android.widget.EditText[contains(@text, 'Password') or @resource-id='login_password']");
    private final By loginButton = AppiumBy.xpath("//android.widget.Button[contains(@text, 'LOGIN') or contains(@text, 'Sign In')]");

    public MobileLoginPage(AndroidDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        waitForVisible(usernameField).sendKeys(username);
        waitForVisible(passwordField).sendKeys(password);
        waitForClickable(loginButton).click();
    }
}