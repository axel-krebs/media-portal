package com.afrnt.portal;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.junit.jupiter.api.Test;

@QuarkusIntegrationTest
public class PingTest {

    @Test
    void ping() {
        System.out.println("Succeeded!");
    }
}
