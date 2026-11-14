package com.rankbook.hrms.automation.drivers.managers;

import com.microsoft.playwright.*;
import com.rankbook.hrms.automation.drivers.capabilities.PlaywrightBrowserOptions;
import com.rankbook.hrms.automation.drivers.factory.TargetEngine;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PlaywrightDriverManager {
    private static final ThreadLocal<Playwright> playwrightThread = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browserThread = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> contextThread = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThread = new ThreadLocal<>();

    public static Page initPage() {
        return initPage(TargetEngine.PLAYWRIGHT_CHROMIUM);
    }

    public static Page initPage(TargetEngine engine) {
        Map<String, String> env = new HashMap<>(System.getenv());
        env.put("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");

        Playwright playwright = Playwright.create(new Playwright.CreateOptions().setEnv(env));
        playwrightThread.set(playwright);

        Browser browser;
        BrowserContext context;

        if (engine == TargetEngine.PLAYWRIGHT_WEBKIT) {
            browser = playwright.webkit().launch(PlaywrightBrowserOptions.getLaunchOptions());
            context = browser.newContext(PlaywrightBrowserOptions.getIosSafariContextOptions());
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
        } else {
            browser = playwright.chromium().launch(PlaywrightBrowserOptions.getLaunchOptions());
            context = browser.newContext(PlaywrightBrowserOptions.getDesktopContextOptions());
        }

        browserThread.set(browser);
        contextThread.set(context);

        Page page = context.newPage();
        pageThread.set(page);
        return page;
    }

    public static Page getPage() {
        return pageThread.get();
    }

    public static void quit() {
        quit(null);
    }

    public static void quit(String traceFileName) {
        if (contextThread.get() != null) {
            if (traceFileName != null) {
                contextThread.get().tracing().stop(new Tracing.StopOptions()
                        .setPath(Paths.get("target/playwright-traces/" + traceFileName + ".zip")));
            }
            contextThread.get().close();
            contextThread.remove();
        }
        if (pageThread.get() != null) {
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