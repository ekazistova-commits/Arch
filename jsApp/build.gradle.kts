plugins {
    kotlin("multiplatform")
}

kotlin {
    js(IR) {
        browser {
        }
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":domain"))
                implementation(project(":memory"))
            }
        }
    }
}