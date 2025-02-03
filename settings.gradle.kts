// settings.gradle.kts (radacina proiectului)

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }

}

rootProject.name = "RuneScroll"

// Include module app
include(":app")
