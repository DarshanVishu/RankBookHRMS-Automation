package com.rankbook.hrms.automation.api.clients;

import com.rankbook.hrms.automation.api.models.requests.EmployeeLocationUpdateDto;
import com.rankbook.hrms.automation.api.specs.RequestSpecFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class EmployeeApiClient {

    private final Map<String, String> authCookies;

    public EmployeeApiClient(Map<String, String> authCookies) {
        this.authCookies = authCookies;
    }

    /**
     * Updates an employee's assigned office location by ID
     * (e.g., locationId 1 = Mysore Office, locationId 2 = WFH)
     */
    public Response updateEmployeeLocation(int employeeNumber, int locationId) {
        EmployeeLocationUpdateDto payload = new EmployeeLocationUpdateDto(locationId);

        return RestAssured.given()
                .spec(RequestSpecFactory.getAuthenticatedRequestSpec(authCookies))
                .body(payload)
                .put("/api/v2/pim/employees/" + employeeNumber + "/job-details");
    }

    /**
     * Fetches current attendance status for an employee
     */
    public Response getAttendanceRecords(int employeeNumber, String date) {
        return RestAssured.given()
                .spec(RequestSpecFactory.getAuthenticatedRequestSpec(authCookies))
                .queryParam("date", date)
                .get("/api/v2/attendance/employees/" + employeeNumber + "/records");
    }
}