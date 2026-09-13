package api.config;

import io.qameta.allure.restassured.AllureRestAssured;

public class AllureFilterFactory {

    public static AllureRestAssured create() {
        return new AllureRestAssured()
                .setRequestTemplate("my-http-request.ftl")
                .setResponseTemplate("my-http-response.ftl");
    }
}