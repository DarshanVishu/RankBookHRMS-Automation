package com.rankbook.hrms.automation.core.config;

public class FrameworkConfig {
    private String orangehrmBaseUrl;
    private String adminUsername;
    private String adminPassword;
    private String appiumServerUrl;
    private String appPackage;
    private String appActivity;

    public String getOrangehrmBaseUrl() { return orangehrmBaseUrl; }
    public void setOrangehrmBaseUrl(String orangehrmBaseUrl) { this.orangehrmBaseUrl = orangehrmBaseUrl; }

    public String getAdminUsername() { return adminUsername; }
    public void setAdminUsername(String adminUsername) { this.adminUsername = adminUsername; }

    public String getAdminPassword() { return adminPassword; }
    public void setAdminPassword(String adminPassword) { this.adminPassword = adminPassword; }

    public String getAppiumServerUrl() { return appiumServerUrl; }
    public void setAppiumServerUrl(String appiumServerUrl) { this.appiumServerUrl = appiumServerUrl; }

    public String getAppPackage() { return appPackage; }
    public void setAppPackage(String appPackage) { this.appPackage = appPackage; }

    public String getAppActivity() { return appActivity; }
    public void setAppActivity(String appActivity) { this.appActivity = appActivity; }
}