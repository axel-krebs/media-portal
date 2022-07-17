plugins {
    id("java")
    id("idea")
    //checkstyle
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

//checkstyle {
    //maxWarnings = 0
    // ...
//}

tasks.withType<JavaCompile> {
    options.isWarnings = true
    // ...
}
