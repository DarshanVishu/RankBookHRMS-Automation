package com.rankbook.hrms.automation.tests.api;

import com.rankbook.hrms.automation.tests.base.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthApiTests extends BaseApiTest {

    @Test(description = "Verify OrangeHRM login page endpoint responds with HTTP 200")
    public void testLoginEndpointHealth() {
        Response response = RestAssured.given()
                .contentType(ContentType.HTML)
                .get("/web/index.php/auth/login");

        Assert.assertEquals(response.getStatusCode(), 200, "Login page HTTP status is not 200 OK");
    }
}