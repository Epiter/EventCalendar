plugins {
    alias(libs.plugins.android.application) // Plugin pentru aplicatii Android
    alias(libs.plugins.kotlin.android) // Plugin pentru Kotlin
    alias(libs.plugins.kotlin.compose) // Plugin pentru Jetpack Compose
    alias(libs.plugins.ksp) // Plugin pentru KSP
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.21"
}

android {
    namespace = "com.example.runescroll"

    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.runescroll"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true // Jetpack Compose
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }

    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/java", "src/main/kotlin")
        }
        getByName("debug") {
            java.srcDirs("src/debug/kotlin", "src/debug/java")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

}

dependencies {

    // Core Android
    implementation(libs.androidx.core.ktx.v1120)
    implementation(libs.androidx.appcompat)

    // Jetpack Compose
    implementation(libs.ui)
    implementation(libs.androidx.material3.v131)
    implementation(libs.ui.tooling.preview)

    // Lifecycle și ViewModel
    implementation(libs.androidx.lifecycle.runtime.ktx.v262)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Room Database + KSP
    implementation(libs.androidx.room.runtime.v252)
    implementation(libs.androidx.room.ktx.v252)
    ksp(libs.androidx.room.compiler.v252)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Accompanist pentru UI extras
    implementation(libs.accompanist.systemuicontroller)

    // Jetpack Navigation
    implementation(libs.androidx.navigation.compose)

    implementation(libs.kotlinx.serialization.json)

    implementation(libs.gson)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)
}