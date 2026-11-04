package com.rankbook.hrms.automation.web.playwright.pages;

import com.microsoft.playwright.Page;

public class PlaywrightLoginPage {
    private final Page page;

    public PlaywrightLoginPage(Page page) {
        this.page = page;
    }

    public void navigate(String url) {
        page.navigate(url);
    }

    public void login(String username, String password) {
        page.locator("input[name='username']").fill(username);
        page.locator("input[name='password']").fill(password);
        page.locator("button[type='submit']").click();
    }
}