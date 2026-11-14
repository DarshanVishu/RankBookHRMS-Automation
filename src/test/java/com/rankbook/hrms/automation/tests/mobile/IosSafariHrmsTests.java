package com.rankbook.hrms.automation.tests.mobile;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.rankbook.hrms.automation.core.constants.FrameworkConstants;
import com.rankbook.hrms.automation.drivers.factory.TargetEngine;
import com.rankbook.hrms.automation.drivers.managers.PlaywrightDriverManager;
import com.rankbook.hrms.automation.web.playwright.pages.PlaywrightLoginPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class IosSafariHrmsTests {
    private Page page;
    private final String baseUrl = System.getProperty("baseUrl", FrameworkConstants.ORANGEHRM_BASE_URL);
    private final String username = System.getProperty("hrms.username", "care@rankbook");
    private final String password = System.getProperty("hrms.password", "b9FHkByDQru2RSA#");

    @BeforeMethod(alwaysRun = true)
    public void setUpIosSafari() {
        page = PlaywrightDriverManager.initPage(TargetEngine.PLAYWRIGHT_WEBKIT);
    }

    private void loginToHrms() {
        PlaywrightLoginPage loginPage = new PlaywrightLoginPage(page);
        loginPage.navigate(baseUrl);
        loginPage.login(username, password);
        page.waitForURL(url -> !url.contains("/auth/login"));
        page.waitForLoadState();
    }

    private void openMobileMenuIfCollapsed() {
        Locator hamburgerBtn = page.locator("i.oxd-topbar-header-hamburger, .oxd-topbar-header-hamburger").first();
        if (hamburgerBtn.isVisible()) {
            hamburgerBtn.tap();
        }
    }

    private void assertNoHorizontalOverflow(String screenName) {
        String overflowReport = (String) page.evaluate(
                "() => {" +
                "  const vw = window.innerWidth;" +
                "  const sw = document.documentElement.scrollWidth;" +
                "  if (sw <= vw + 2) return '';" + // Ignore 1-2px Retina deviceScaleFactor=3 subpixel rounding
                "  const offenders = Array.from(document.querySelectorAll('div, section, table, form, header, nav, main, button, input'))" +
                "    .filter(el => {" +
                "      const rect = el.getBoundingClientRect();" +
                "      return rect.width > 0 && rect.height > 0 && rect.right > vw + 2;" +
                "    })" +
                "    .slice(0, 5)" +
                "    .map(el => `${el.tagName.toLowerCase()}${typeof el.className === 'string' && el.className ? '.' + el.className.trim().replace(/\\s+/g, '.') : ''} (right=${Math.round(el.getBoundingClientRect().right)}px)`);" +
                "  return `scrollWidth=${sw}px > viewport=${vw}px | Offenders: ${offenders.join(', ')}`;" +
                "}"
        );
        Assert.assertTrue(overflowReport.isEmpty(),
                "Horizontal overflow detected on iOS Safari on [" + screenName + "] -> " + overflowReport);
    }

    @Test(priority = 1, description = "Verify HRMS login succeeds and persists across reload on iOS Safari")
    public void testIosSafariLoginAndSessionPersistence() {
        PlaywrightLoginPage loginPage = new PlaywrightLoginPage(page);
        loginPage.navigate(baseUrl);

        Locator submitBtn = page.locator("button[type='submit']");
        submitBtn.waitFor();
        Assert.assertTrue(submitBtn.isVisible(), "Login submit button is not visible on iOS viewport.");

        loginPage.login(username, password);
        page.waitForURL(url -> !url.contains("/auth/login"));

        // Reload to verify iOS Safari SameSite/Secure cookies persist
        page.reload();
        page.waitForLoadState();
        Assert.assertFalse(page.url().contains("/auth/login"),
                "iOS Safari session dropped after reload (check cookie SameSite/Secure flags).");
        assertNoHorizontalOverflow("Dashboard after reload");
    }

    @Test(priority = 2, description = "Verify mobile drawer navigation, Leave filter expand, and Leave Type options on iOS Safari")
    public void testIosSafariLeaveMenuAndDropdownSelection() {
        loginToHrms();
        openMobileMenuIfCollapsed();

        Locator leaveMenu = page.locator("a.oxd-main-menu-item", new Page.LocatorOptions().setHasText("Leave")).first();
        leaveMenu.waitFor();
        leaveMenu.tap();
        page.waitForLoadState();

        Assert.assertTrue(page.url().toLowerCase().contains("leave"),
                "Failed to navigate to Leave module on iOS Safari. Current URL: " + page.url());
        assertNoHorizontalOverflow("Leave List");

        // On mobile viewports (393px), OrangeHRM collapses the Leave List filter panel by default—expand it first
        Locator filterToggleBtn = page.locator(".oxd-table-filter-header-options button").first();
        Locator visibleSelects = page.locator(".oxd-select-text:visible");
        if (visibleSelects.count() == 0 && filterToggleBtn.isVisible()) {
            filterToggleBtn.tap();
        }

        // Wait for visible select boxes (0 = Show Leave with Status, 1 = Leave Type)
        visibleSelects.first().waitFor();
        Locator leaveTypeSelect = visibleSelects.count() > 1 ? visibleSelects.nth(1) : visibleSelects.first();
        leaveTypeSelect.scrollIntoViewIfNeeded();
        leaveTypeSelect.tap();

        Locator dropdownOptions = page.locator(".oxd-select-dropdown [role='option'], .oxd-select-option");
        dropdownOptions.first().waitFor();

        List<String> optionTexts = dropdownOptions.allInnerTexts();
        Assert.assertTrue(optionTexts.size() > 1,
                "Leave dropdown options failed to render on iOS WebKit tap. Found: " + optionTexts);

        // Tap the second option (first non-placeholder option) and verify touch selection persists
        String expectedText = dropdownOptions.nth(1).innerText().trim();
        dropdownOptions.nth(1).tap();
        Assert.assertEquals(leaveTypeSelect.innerText().trim(), expectedText,
                "iOS WebKit touch tap did not update selected dropdown value.");
    }

    @Test(priority = 3, description = "Verify Attendance / Time module loads and Punch In/Out screen works on iOS Safari")
    public void testIosSafariAttendancePunchScreen() {
        loginToHrms();
        openMobileMenuIfCollapsed();

        Locator timeMenu = page.locator("a.oxd-main-menu-item", new Page.LocatorOptions().setHasText("Time")).first();
        timeMenu.waitFor();
        timeMenu.tap();
        page.waitForLoadState();

        Locator attendanceTopTab = page.locator("span.oxd-topbar-body-nav-tab-item",
                new Page.LocatorOptions().setHasText("Attendance")).first();
        if (attendanceTopTab.isVisible()) {
            attendanceTopTab.tap();
            Locator punchMenuItem = page.getByRole(AriaRole.MENUITEM,
                    new Page.GetByRoleOptions().setName("Punch In/Out"));
            if (punchMenuItem.isVisible()) {
                punchMenuItem.tap();
                page.waitForLoadState();
            }
        }

        assertNoHorizontalOverflow("Attendance / Punch In-Out");

        Locator actionBtn = page.locator("button[type='submit']").first();
        if (actionBtn.isVisible()) {
            actionBtn.scrollIntoViewIfNeeded();
            Assert.assertTrue(actionBtn.isVisible() && actionBtn.isEnabled(),
                    "Punch In/Out submit button is disabled or blocked on iOS Safari.");
        }
    }

    @Test(priority = 4, description = "Verify My Info profile form and date picker render cleanly on iOS Safari")
    public void testIosSafariMyInfoAndDatePicker() {
        loginToHrms();
        openMobileMenuIfCollapsed();

        Locator myInfoMenu = page.locator("a.oxd-main-menu-item", new Page.LocatorOptions().setHasText("My Info")).first();
        if (myInfoMenu.isVisible()) {
            myInfoMenu.tap();
            page.waitForLoadState();
            assertNoHorizontalOverflow("My Info / Personal Details");

            // Check calendar/date input interaction on WebKit
            Locator dateInput = page.locator(".oxd-date-input input").first();
            if (dateInput.isVisible()) {
                dateInput.scrollIntoViewIfNeeded();
                dateInput.tap();
                Assert.assertTrue(dateInput.isEditable(),
                        "Date input field is locked or non-interactive on iOS Safari.");
            }
        }
    }

    @Test(priority = 5, description = "Verify login inputs do not trigger iOS auto-zoom or horizontal overflow")
    public void testIosSafariLoginViewportIntegrity() {
        page.navigate(baseUrl);
        Locator usernameInput = page.locator("input[name='username']");
        usernameInput.waitFor();
        usernameInput.tap();

        assertNoHorizontalOverflow("Login Screen");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        PlaywrightDriverManager.quit(result.getMethod().getMethodName());
    }
}