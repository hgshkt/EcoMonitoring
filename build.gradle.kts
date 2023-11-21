plugins {
    kotlin("jvm") version "1.9.0"
    id("org.openjfx.javafxplugin") version "0.0.8"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("no.tornado:tornadofx:1.7.20")
    implementation("io.github.evanrupert:excelkt:1.0.2")
    implementation ("mysql:mysql-connector-java:8.0.28")
}

tasks.test {
    useJUnitPlatform()
}
javafx {
    version = "11.0.2"
    modules("javafx.controls", "javafx.graphics")
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}