package com.afrtn.portal;

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test
import org.hamcrest.Matchers.equalTo

@QuarkusTest
class TestResource {
    @Test
    fun testIndexEndpoint() {
        given()
            .`when`().get("/")
            .then()
            .statusCode(200)
            .body("message", equalTo("Index"));
    }
}