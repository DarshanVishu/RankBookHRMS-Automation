package com.rankbook.hrms.automation.api.specs;

import com.rankbook.hrms.automation.core.constants.FrameworkConstants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public final class RequestSpecFactory {

    private RequestSpecFactory() {}

    public static RequestSpecification getBaseRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(FrameworkConstants.ORANGEHRM_BASE_URL)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .build();
    }

    public static RequestSpecification getAuthenticatedRequestSpec(Map<String, String> cookies) {
        return new RequestSpecBuilder()
                .addRequestSpecification(getBaseRequestSpec())
                .addCookies(cookies)
                .build();
    }
}