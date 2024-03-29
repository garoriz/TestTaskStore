pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "TestStore"
include(":app")
include(":core")
include(":registration-feature")
include(":database")
include(":main-feature")
include(":catalog-feature")
include(":network")
include(":item-feature")
include(":personal-profile-feature")
include(":favorites-feature")
