package com.rankbook.hrms.automation.api.clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LocationApiClient {
    private final String baseUrl;

    public LocationApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Response getLocations(String sessionCookie) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .cookie("orangehrm", sessionCookie)
                .get("/api/v2/admin/locations");
    }
}