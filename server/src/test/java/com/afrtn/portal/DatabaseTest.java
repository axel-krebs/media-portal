package com.afrtn.portal;


import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.utility.DockerImageName;

//@Testcontainers
public class DatabaseTest {
    //@Container
    private static Neo4jContainer<?> neo4jContainer =
            new Neo4jContainer<>(DockerImageName.parse("neo4j:4.4"))
                    .withAdminPassword(null); // Disable password
}
