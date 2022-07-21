package com.afrtn.portal;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToIgnoringCase;

@QuarkusTest
class TestResource {

    @Test
    void testIndexEndpoint() {
        given()
                .when().get("/")
                .then()
                .statusCode(200)
                .body("message", equalToIgnoringCase("Index"));
    }
}