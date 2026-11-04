package com.rankbook.hrms.automation.mobile.utils;

import io.appium.java_client.android.AndroidDriver;

import java.io.InputStream;

public final class GeoMockingUtil {

    private GeoMockingUtil() {
        // Prevent instantiation
    }

    /**
     * Injects GPS coordinates into the active Android device / emulator via ADB.
     */
    public static void setLocation(AndroidDriver driver, double latitude, double longitude, double altitude) {
        setEmulatorLocationViaAdb(longitude, latitude);
    }

    /**
     * Injects GPS coordinates directly into Android Emulator via ADB telnet / geo fix.
     * Uses transferTo(OutputStream.nullOutputStream()) to drain stdout without unused local variables.
     */
    public static void setEmulatorLocationViaAdb(double longitude, double latitude) {
        try {
            Process process = Runtime.getRuntime().exec(
                    String.format("adb emu geo fix %f %f", longitude, latitude)
            );
            try (InputStream is = process.getInputStream()) {
                is.transferTo(java.io.OutputStream.nullOutputStream());
            }
            process.waitFor();
        } catch (Exception e) {
            System.err.println("[ERROR] Failed to inject location via ADB emu geo fix: " + e.getMessage());
        }
    }
}