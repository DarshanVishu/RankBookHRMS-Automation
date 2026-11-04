package com.rankbook.hrms.automation.tests.base;

import com.rankbook.hrms.automation.core.constants.FrameworkConstants;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest {
    protected String baseUri;

    @BeforeClass(alwaysRun = true)
    public void setupApi() {
        this.baseUri = FrameworkConstants.ORANGEHRM_BASE_URL;
        RestAssured.baseURI = this.baseUri;
    }
}