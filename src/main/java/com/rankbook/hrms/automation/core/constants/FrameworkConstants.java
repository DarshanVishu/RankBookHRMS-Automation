package com.rankbook.hrms.automation.core.constants;

import java.time.Duration;

public final class FrameworkConstants {

    private FrameworkConstants() {}

    // Timeouts
    public static final Duration EXPLICIT_WAIT = Duration.ofSeconds(15);
    public static final Duration IMPLICIT_WAIT = Duration.ofSeconds(10);

    // Appium Android Configuration
    public static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    public static final String APP_PACKAGE = "com.rankbook.hrms";
    public static final String APP_ACTIVITY = "com.rankbook.hrms.MainActivity";

    // OrangeHRM Web
    public static final String ORANGEHRM_BASE_URL = "https://test.orangehrm.savista.tech";

    // Mysore Office Geofence Coordinates
    public static final double MYSORE_OFFICE_LATITUDE = 12.286291;
    public static final double MYSORE_OFFICE_LONGITUDE = 76.627331;
    public static final double DEFAULT_ALTITUDE = 750.0;
}