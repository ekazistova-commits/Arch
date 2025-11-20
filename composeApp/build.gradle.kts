plugins {
    kotlin("multiplatform")
    id("com.android.application")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":memory"))
                implementation("androidx.appcompat:appcompat:1.6.1")
                implementation("androidx.core:core-ktx:1.12.0")
            }
        }
    }
}

android {
    namespace = "ru.otus.arch.composeapp"
    compileSdk = 34
    
    defaultConfig {
        applicationId = "ru.otus.arch.composeapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
