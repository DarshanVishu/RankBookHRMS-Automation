package com.rankbook.hrms.automation.api.clients;

import com.rankbook.hrms.automation.api.specs.RequestSpecFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class AuthApiClient {

    /**
     * Obtains the authenticated session cookies from OrangeHRM
     */
    public Map<String, String> loginAndGetSessionCookies(String username, String password) {
        // 1. Initial GET request to capture base CSRF token and initial session cookie
        Response initialResponse = RestAssured.given()
                .spec(RequestSpecFactory.getBaseRequestSpec())
                .contentType("application/x-www-form-urlencoded")
                .get("/auth/login");

        Map<String, String> cookies = initialResponse.getCookies();

        // 2. Perform authentication POST
        Response authResponse = RestAssured.given()
                .spec(RequestSpecFactory.getBaseRequestSpec())
                .contentType("application/x-www-form-urlencoded")
                .cookies(cookies)
                .formParam("username", username)
                .formParam("password", password)
                .post("/auth/validate");

        // Merge latest cookies containing the active session
        cookies.putAll(authResponse.getCookies());
        return cookies;
    }
}