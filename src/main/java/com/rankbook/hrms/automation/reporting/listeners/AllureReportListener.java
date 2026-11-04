package com.rankbook.hrms.automation.reporting.listeners;

import com.rankbook.hrms.automation.drivers.managers.AppiumDriverManager;
import com.rankbook.hrms.automation.drivers.managers.SeleniumDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AllureReportListener implements ITestListener {

    @Attachment(value = "Web Failure Screenshot", type = "image/png")
    public byte[] captureWebScreenshot(WebDriver driver) {
        if (driver instanceof TakesScreenshot) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }

    @Attachment(value = "Mobile Failure Screenshot", type = "image/png")
    public byte[] captureMobileScreenshot(WebDriver driver) {
        if (driver instanceof TakesScreenshot) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }

    @Attachment(value = "Android Logcat Dump", type = "text/plain")
    public String captureLogcat() {
        StringBuilder logcatOutput = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec("adb logcat -d -t 150");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                logcatOutput.append(line).append("\n");
            }
        } catch (Exception e) {
            return "Failed to extract Logcat: " + e.getMessage();
        }
        return logcatOutput.toString();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // 1. Capture Mobile failure state if active
        if (AppiumDriverManager.getDriver() != null) {
            captureMobileScreenshot(AppiumDriverManager.getDriver());
            captureLogcat();
        }

        // 2. Capture Web failure state if active
        if (SeleniumDriverManager.getDriver() != null) {
            captureWebScreenshot(SeleniumDriverManager.getDriver());
        }
    }

    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}