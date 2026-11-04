package com.rankbook.hrms.automation.drivers.managers;

import com.microsoft.playwright.*;
import com.rankbook.hrms.automation.drivers.capabilities.PlaywrightBrowserOptions;

public class PlaywrightDriverManager {
    private static final ThreadLocal<Playwright> playwrightThread = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browserThread = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThread = new ThreadLocal<>();

    public static Page initPage() {
        Playwright playwright = Playwright.create();
        playwrightThread.set(playwright);
        Browser browser = playwright.chromium().launch(PlaywrightBrowserOptions.getLaunchOptions());
        browserThread.set(browser);
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080));
        Page page = context.newPage();
        pageThread.set(page);
        return page;
    }

    public static Page getPage() {
        return pageThread.get();
    }

    public static void quit() {
        if (pageThread.get() != null) {
            pageThread.get().context().browser().close();
            pageThread.remove();
        }
        if (browserThread.get() != null) {
            browserThread.get().close();
            browserThread.remove();
        }
        if (playwrightThread.get() != null) {
            playwrightThread.get().close();
            playwrightThread.remove();
        }
    }
}