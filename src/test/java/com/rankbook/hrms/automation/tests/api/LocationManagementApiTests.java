package com.rankbook.hrms.automation.tests.api;

import com.rankbook.hrms.automation.api.clients.LocationApiClient;
import com.rankbook.hrms.automation.tests.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LocationManagementApiTests extends BaseApiTest {

    @Test(description = "Verify location list API requires valid authentication")
    public void testGetLocationsRequiresAuth() {
        LocationApiClient client = new LocationApiClient(baseUri);
        Response response = client.getLocations("unauthorized_token");

        Assert.assertTrue(
            response.getStatusCode() == 302 || response.getStatusCode() == 401 || response.getStatusCode() == 403,
            "Expected secure status code on unauthenticated location retrieval"
        );
    }
}