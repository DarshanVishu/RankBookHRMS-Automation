package com.rankbook.hrms.automation.reporting.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Initializing ExtentReport execution suite context: " + context.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Stub for ExtentTest pass hook
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Stub for ExtentTest fail & screenshot hook
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Flushing ExtentReport for suite: " + context.getName());
    }
}