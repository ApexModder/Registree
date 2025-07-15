pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    versionCatalogs.create("libs") {
        plugin("moddevgradle", "net.neoforged.moddev").version {
            strictly("[2.0.72,2.1.0)")
        }

        plugin("ideaext", "org.jetbrains.gradle.plugin.idea-ext").version("1.1.9")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "Registree"
