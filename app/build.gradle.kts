plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    id("com.diffplug.spotless") version "6.25.0"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()

    flatDir {
        dirs("local/bridge")
    }
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("bridge:bridge-0.0.0-alpha.1.0.0+git.8358b6c")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    // Define the main class for the application.
    mainClass = "com.tic_tac_toe.App"
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

spotless {
  java {
    googleJavaFormat("1.22.0").aosp().reflowLongStrings().skipJavadocFormatting()
    // fix formatting of type annotations
    formatAnnotations()
  }
}
