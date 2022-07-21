buildscript {

}

plugins {
    //id("java") // wish I don't need it.
    kotlin("js") version "1.7.10"
}

group = "de.akrebs.web"
version = "1.0.0-SNAPSHOT"

kotlin {
    js {
        browser()
    }
    kotlinDaemonJvmArgs = listOf("-Xmx1024m", "-Xms512m", "-XX:+UseParallelGC")
    sourceSets {
        val main by getting {
            dependencies {
                //api("io.quarkus:quarkus-bom:$quarkusVersion") s. dependencies above
                implementation(kotlin("script-runtime"))
            }
        }
        val test by getting {
            dependencies {
                implementation(kotlin("test")) // This brings all the platform dependencies automatically
            }
        }
    }
}
//tasks.register<Wrapper>("wrapper") {
//
//}

tasks.register("prepareKotlinBuildScriptModel"){

}
