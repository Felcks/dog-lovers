pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Dog lovers"
include(":app")
include(":core:presentation")
include(":core:domain")
include(":core:network")
include(":dogs:domain")
include(":dogs:domainImpl")
include(":dogs:network")
include(":dogs:presentation")
