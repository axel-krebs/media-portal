buildscript {
    // put global here
}

plugins {
    //id("java-library-conventions")
    java
    kotlin("jvm") version "1.7.10"
    id("io.quarkus") version "2.10.2.Final"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.7.10"
    id("jacoco")
    //id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    //id("org.kordamp.gradle.jandex") version "0.12.0"
    //id("org.sonarqube").version("3.3")
}

group = "de.akrebs.web"
version = "1.0.0-SNAPSHOT"

val kotlinVersion by extra("1.7.10")
val javaMajorVersion = "11" // Kotlin-Java
val quarkusVersion = "2.10.2.Final"
val log4jVersion = "1.2.17"
val assertjVersion = "3.23.1"
val restAssuredVersion = "5.1.1"
val smallryeVertxClientVersion = "2.24.1"
val quarkiversNeo4JVersion = "1.3.2"
val testcontainersNeo4JVersion = "1.17.3"

configurations {

}

dependencies {
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:$quarkusVersion"))
    //implementation("io.quarkus:quarkus-arc:$quarkusVersion")
    implementation("io.quarkus:quarkus-config-yaml")
    implementation("io.quarkus:quarkus-kotlin:$quarkusVersion")
    //implementation("io.quarkus:quarkus-hibernate-validator:$quarkusVersion")
    //implementation("io.quarkus:quarkus-resteasy-reactive:$quarkusVersion")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson:$quarkusVersion")
    //implementation("io.quarkus:quarkus-resteasy-reactive-qute:$quarkusVersion")
    implementation("io.quarkus:quarkus-vertx:$quarkusVersion")
    implementation("io.quarkus:quarkus-vertx-web:$quarkusVersion")
    implementation("io.quarkus:quarkus-reactive-routes:$quarkusVersion")
    //implementation("io.quarkus:quarkus-hibernate-reactive-panache:$quarkusVersion")
    //implementation("org.hibernate.reactive:hibernate-reactive-core:1.1.7.Final")
    implementation("io.quarkiverse.neo4j:quarkus-neo4j:$quarkiversNeo4JVersion")
    //implementation("log4j:log4j:$log4jVersion")
    testImplementation("org.jboss.logmanager:log4j2-jboss-logmanager")
    // test f/w
    testImplementation("io.quarkus:quarkus-junit5:$quarkusVersion")
    testImplementation("io.rest-assured:rest-assured:$restAssuredVersion")
    testImplementation("io.smallrye.reactive:smallrye-mutiny-vertx-web-client:$smallryeVertxClientVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("org.hamcrest:hamcrest-all:1.3")
    testImplementation("org.testcontainers:testcontainers:$testcontainersNeo4JVersion")
    testImplementation("org.testcontainers:neo4j:$testcontainersNeo4JVersion")
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    //annotation("io.quarkus.runtime.annotations.QuarkusMain")
    annotation("io.quarkus.test.junit.QuarkusTest")
    //annotation("io.quarkus.vertx.web.Route")
}

java {
    val kotlinSrcDir = "src/main/kotlin"
    val mainJavaSourceSet: SourceDirectorySet = sourceSets.getByName("main").java
    mainJavaSourceSet.srcDir(kotlinSrcDir)
    println(mainJavaSourceSet.srcDirs)
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks

val compileTestKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks

compileTestKotlin.kotlinOptions {
    jvmTarget = javaMajorVersion
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    kotlinOptions.javaParameters = true
}

tasks.quarkusDev {
    compilerOptions {
        compiler("kotlin").args(listOf("-Werror"))
    }
    //workingDirectory.set(rootProject.projectDir)
}

tasks.withType<io.quarkus.gradle.tasks.QuarkusBuild> {
    nativeArgs {
        "container-build" to true
        "builder-image" to "axel9691/ubu20-jdk11-node10:temp"
    }
}

tasks.withType<GenerateModuleMetadata>().configureEach {
    suppressedValidationErrors.add("enforced-platform")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "Bootstrap"
    }
}