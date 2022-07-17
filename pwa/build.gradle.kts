buildscript {

}

plugins {
    id("reactive-web-conventions")
    //id("java") // wish I don't need it.
    kotlin("js") version "1.7.10"
}

group = "de.akrebs.web"
version = "1.0.0-SNAPSHOT"

kotlin {
    js {
        browser()
    }
}