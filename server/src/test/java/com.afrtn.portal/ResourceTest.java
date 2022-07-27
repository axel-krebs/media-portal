package com.afrtn.portal;

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test

@QuarkusTest
class ResourceTest {
    @Test
    void testIndexEndpoint() {
        given().contentType(ContentType.HTML)
            .when().get("/")
            .then()
            .statusCode(200)
            .body(containsString("This is the index page"));
    }
}