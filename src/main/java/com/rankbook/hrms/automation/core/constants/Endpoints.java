package com.rankbook.hrms.automation.core.constants;

public final class Endpoints {

    private Endpoints() {}

    // ==========================================
    // 1. OrangeHRM Web UI Route Paths
    // ==========================================
    public static final String WEB_LOGIN = "/web/index.php/auth/login";
    public static final String WEB_LOGOUT = "/web/index.php/auth/logout";
    public static final String WEB_DASHBOARD = "/web/index.php/dashboard/index";
    public static final String WEB_ATTENDANCE_RECORD = "/web/index.php/attendance/viewAttendanceRecord";
    public static final String WEB_MY_ATTENDANCE_RECORD = "/web/index.php/attendance/viewMyAttendanceRecord";
    public static final String WEB_PUNCH_IN_OUT = "/web/index.php/attendance/punchIn";
    public static final String WEB_EMPLOYEE_TIMESHEET = "/web/index.php/time/viewEmployeeTimesheet";
    public static final String WEB_MY_TIMESHEET = "/web/index.php/time/viewMyTimesheet";
    public static final String WEB_PIM_EMPLOYEE_LIST = "/web/index.php/pim/viewEmployeeList";
    public static final String WEB_ADMIN_LOCATIONS = "/web/index.php/admin/viewLocations";
    public static final String WEB_ADMIN_USERS = "/web/index.php/admin/viewSystemUsers";

    // ==========================================
    // 2. OrangeHRM v2 REST API Endpoints
    // ==========================================
    // Auth & Token
    public static final String API_AUTH_TOKEN = "/oauth/issueToken";
    public static final String API_AUTH_VALIDATE = "/api/v2/auth/validate";

    // Attendance & Punch Operations
    public static final String API_ATTENDANCE_RECORDS = "/api/v2/attendance/records/{empNumber}";
    public static final String API_ATTENDANCE_PUNCH_IN = "/api/v2/attendance/punch-in";
    public static final String API_ATTENDANCE_PUNCH_OUT = "/api/v2/attendance/punch-out";
    public static final String API_ATTENDANCE_CURRENT_STATE = "/api/v2/attendance/records/current-state/{empNumber}";
    public static final String API_ATTENDANCE_CONFIG = "/api/v2/attendance/configuration";

    // PIM & Employees
    public static final String API_PIM_EMPLOYEES = "/api/v2/pim/employees";
    public static final String API_PIM_EMPLOYEE_DETAIL = "/api/v2/pim/employees/{empNumber}";
    public static final String API_PIM_EMPLOYEE_JOB = "/api/v2/pim/employees/{empNumber}/job-details";

    // Admin & Organization Locations
    public static final String API_ADMIN_LOCATIONS = "/api/v2/admin/locations";
    public static final String API_ADMIN_LOCATION_BY_ID = "/api/v2/admin/locations/{id}";
    public static final String API_ADMIN_USERS = "/api/v2/admin/users";

    // Timesheets
    public static final String API_TIMESHEETS = "/api/v2/time/timesheets";
    public static final String API_TIMESHEETS_PENDING_ACTION = "/api/v2/time/timesheets/pending-action";

    // ==========================================
    // 3. Mobile Backend / Sync Endpoints
    // ==========================================
    public static final String MOBILE_API_LOGIN = "/api/v1/mobile/auth/login";
    public static final String MOBILE_API_GEOFENCE_VALIDATE = "/api/v1/mobile/geofence/validate";
    public static final String MOBILE_API_PUNCH_SYNC = "/api/v1/mobile/attendance/sync";
    public static final String MOBILE_API_ATTENDANCE_HISTORY = "/api/v1/mobile/attendance/history";
}