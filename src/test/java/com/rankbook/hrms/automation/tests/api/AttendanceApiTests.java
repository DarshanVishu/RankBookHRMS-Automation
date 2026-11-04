package com.rankbook.hrms.automation.tests.api;

import com.rankbook.hrms.automation.api.models.requests.PunchInRequestDto;
import com.rankbook.hrms.automation.api.models.requests.PunchOutRequestDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AttendanceApiTests {
    private final String baseUrl;

    public AttendanceApiTests(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Response getEmployeeAttendance(String employeeId, String date, String sessionCookie) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .cookie("orangehrm", sessionCookie)
                .queryParam("date", date)
                .get("/api/v2/attendance/records/" + employeeId);
    }

    public Response punchIn(PunchInRequestDto payload, String sessionCookie) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .cookie("orangehrm", sessionCookie)
                .body(payload)
                .post("/api/v2/attendance/punch-in");
    }

    public Response punchOut(PunchOutRequestDto payload, String sessionCookie) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .cookie("orangehrm", sessionCookie)
                .body(payload)
                .post("/api/v2/attendance/punch-out");
    }
}