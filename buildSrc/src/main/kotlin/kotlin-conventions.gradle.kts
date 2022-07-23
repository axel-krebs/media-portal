import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    id("java")
    "kotlin-dsl"
    "idea"
    //checkstyle
}

tasks.withType<JavaCompile> {
    println("Setting Java compiler options in kotlin-conventions!")
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

tasks.named("compileTestJava").configure {
    println("Setting Java TEST compiler options in kotlin-conventions!")
}

//checkstyle {
    //maxWarnings = 0
    // ...
//}
