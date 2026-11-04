package com.rankbook.hrms.automation.web.playwright.pages;

import com.microsoft.playwright.Page;

public class PlaywrightDashboardPage {
    private final Page page;

    public PlaywrightDashboardPage(Page page) {
        this.page = page;
    }

    public boolean isDashboardVisible() {
        return page.locator(".oxd-topbar-header-title").isVisible();
    }

    public String getUserGreeting() {
        return page.locator(".oxd-userdropdown-name").innerText();
    }
}