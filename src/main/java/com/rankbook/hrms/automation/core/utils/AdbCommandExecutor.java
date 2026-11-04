package com.rankbook.hrms.automation.core.utils;

import com.rankbook.hrms.automation.mobile.utils.SystemProcessUtil;

public final class AdbCommandExecutor {
    private AdbCommandExecutor() {}

    public static String setMockGpsLocation(double latitude, double longitude) {
        return SystemProcessUtil.execute("adb emu geo fix " + longitude + " " + latitude);
    }

    public static String stopApp(String appPackage) {
        return SystemProcessUtil.execute("adb shell am force-stop " + appPackage);
    }

    public static String clearAppData(String appPackage) {
        return SystemProcessUtil.execute("adb shell pm clear " + appPackage);
    }
}