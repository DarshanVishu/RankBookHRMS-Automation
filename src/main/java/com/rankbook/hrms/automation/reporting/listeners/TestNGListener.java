package com.rankbook.hrms.automation.reporting.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(">>> [STARTING TEST]: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(">>> [PASSED]: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.err.println(">>> [FAILED]: " + result.getName() + " | Cause: " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(">>> [SKIPPED]: " + result.getName());
    }

    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}