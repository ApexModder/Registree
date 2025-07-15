import org.slf4j.event.Level
import java.text.SimpleDateFormat
import java.util.*

plugins {
    `java-library`

    alias(libs.plugins.ideaext)
    alias(libs.plugins.moddevgradle)
}

val IS_CI = System.getenv("IS_CI").toBoolean()

version = "21.7.0"
group = "dev.apexstudios"
base.archivesName = project.name.lowercase()

idea.module {
    if(!IS_CI) {
        isDownloadSources = true
        isDownloadJavadoc = true
    }

    excludeDirs.addAll(files(
        ".gradle",
        ".idea",
        "gradle",
    ))
}

neoForge {
    version = "21.7.11-beta"
    addModdingDependenciesTo(sourceSets[SourceSet.TEST_SOURCE_SET_NAME])

    parchment {
        minecraftVersion = "1.21.5"
        mappingsVersion = "2025.06.15"
    }

    mods {
        create("registree") {
            sourceSet(sourceSets[SourceSet.MAIN_SOURCE_SET_NAME])
        }

        create("testmod") {
            sourceSet(sourceSets[SourceSet.TEST_SOURCE_SET_NAME])
        }
    }

    runs {
        create("client") {
            client()
        }

        create("server") {
            server()
        }

        configureEach {
            logLevel.set(Level.DEBUG)
            gameDirectory.set(type.map { layout.projectDirectory.dir("run/$it") })
            sourceSet.set(sourceSets[SourceSet.TEST_SOURCE_SET_NAME])
            loadedMods.set(mods)
            systemProperty("terminal.ansi", "true") // fix terminal not having colors

            jvmArguments.addAll(listOf(
                "-XX:+AllowEnhancedClassRedefinition",
                "-XX:+IgnoreUnrecognizedVMOptions",
                "-XX:+AllowRedefinitionToAddDeleteMethods",
                "-XX:+ClassUnloading"
            ))
        }
    }
}

java {
    toolchain {
        vendor.set(JvmVendorSpec.JETBRAINS)
    }

    withSourcesJar()
}

tasks.withType(Jar::class.java) {
    manifest {
        attributes.putAll(mutableMapOf(
            "Specification-Title" to project.name,
            "Specification-Vendor" to "ApexStudios",
            "Specification-Version" to "1",

            "Implementation-Title" to project.name,
            "Implementation-Vendor" to "ApexStudios",
            "Implementation-Version" to project.version,
            "Implementation-Timtstamp" to SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ssZ").format(Date())
        ))
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
