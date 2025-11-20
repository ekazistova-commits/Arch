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

rootProject.name = "Arch"

include(":domain")
include(":memory")
include(":composeApp")
include(":jsApp")
// include(":shared")
// include(":server")
// include(":domainmock")
// include(":datastore")
// include(":basicauth")