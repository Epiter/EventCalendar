plugins {
    alias(libs.plugins.android.application) // Plugin pentru aplicatii Android
    alias(libs.plugins.kotlin.android) // Plugin pentru Kotlin
    alias(libs.plugins.kotlin.compose) // Plugin pentru Jetpack Compose
    alias(libs.plugins.ksp) // Plugin pentru KSP
}

android {
    namespace = "com.example.eventcalendar"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.eventcalendar"
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

    // AndroidX Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)

    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Testare
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Room Database pentru gestionarea bazei de date
    implementation(libs.room.runtime) // Biblioteca principala Room
    ksp(libs.room.compiler) // Necesita KPS pentru generarea codului Room
    implementation(libs.room.ktx) // Suport pentru coroutines in Room
}