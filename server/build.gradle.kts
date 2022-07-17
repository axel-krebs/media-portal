import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
buildscript {

}

plugins {
    id("java-library-conventions")
    //id("io.quarkus") version "2.11.0.CR1"
    kotlin("jvm") version "1.7.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.7.10"
    application
}

group = "de.akrebs.web"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val javaMajorVersion = "17"
val log4jVersion = "1.2.17"
val quarkusVersion = "2.10.2.Final"
val vertxVersion = "4.3.2"
val assertjVersion = "3.23.1"
val kotlinVersion by extra("1.7.10")

sourceSets.main {
    java.srcDirs("src/main/java", "src/main/kotlin")
}

application {
    mainClass.set("Bootstrap")
}

java {
    sourceCompatibility = JavaVersion.toVersion(javaMajorVersion)
    targetCompatibility = JavaVersion.toVersion(javaMajorVersion)
    //val kotlinSrcDir = "src/main/kotlin"
    val mainJavaSourceSet: SourceDirectorySet = sourceSets.getByName("main").java
    //mainJavaSourceSet.srcDir(kotlinSrcDir)
    println(mainJavaSourceSet.srcDirs)
}

kotlin {
    //jvmToolchain {
    //    languageVersion.set(JavaLanguageVersion.of(javaMajorVersion)) // "8"
    //}
    kotlinDaemonJvmArgs = listOf("-Xmx1024m", "-Xms512m", "-XX:+UseParallelGC")
    sourceSets {
        val main by getting {
            dependencies {
                api("io.quarkus:quarkus-bom:$quarkusVersion")
                implementation("io.quarkus:quarkus-resteasy-reactive:$quarkusVersion")
                implementation("io.quarkus:quarkus-resteasy-reactive-jackson:$quarkusVersion")
                implementation("io.quarkus:quarkus-resteasy-reactive-qute:$quarkusVersion")
                implementation("io.quarkus:quarkus-hibernate-reactive-panache:$quarkusVersion")
                implementation("io.quarkus:quarkus-kotlin:$quarkusVersion")
                implementation("io.quarkus:quarkus-vertx:$quarkusVersion")
                implementation("io.quarkus:quarkus-arc:$quarkusVersion")
                //implementation "io.vertx:vertx-lang-groovy:$vertxVersion"
                implementation("io.vertx:vertx-core:$vertxVersion")
                implementation("io.vertx:vertx-config:$vertxVersion")
                implementation("log4j:log4j:$log4jVersion")
                implementation(kotlin("script-runtime"))
            }
        }
        val test by getting {
            dependencies {
                implementation(kotlin("test")) // This brings all the platform dependencies automatically
                implementation("org.assertj:assertj-core:$assertjVersion")
                implementation("io.quarkus:quarkus-junit5:$quarkusVersion")
                implementation("io.rest-assured:rest-assured:5.1.1")
                implementation("io.smallrye.reactive:smallrye-mutiny-vertx-web-client:2.24.1")
            }
        }
    }
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks

compileKotlin.kotlinOptions {
    jvmTarget = "$javaMajorVersion"
}

val compileTestKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks

compileTestKotlin.kotlinOptions {
    jvmTarget = "$javaMajorVersion"
}

tasks.test {
    systemProperty("quarkus.test.profile", "it")
}
