plugins {
    java
    id("org.springframework.boot") version "4.1.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "dev.arcturuz"
version = "0.0.1-SNAPSHOT"
description = "TaskManager"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {

    // variables
    val mapstructVersion = "1.6.3"
    val lombokVersion = "1.18.46"
    val lombokMapstructBindingVersion = "0.2.0"

    // implementation
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.mapstruct:mapstruct:${mapstructVersion}")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // testImplementation
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")

    //testRuntimeOnly
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // compile
    compileOnly("org.projectlombok:lombok:${lombokVersion}")

    // runtime
    runtimeOnly("com.h2database:h2")

    // annotation processor
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:${lombokMapstructBindingVersion}")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
