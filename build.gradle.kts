import com.diffplug.gradle.spotless.SpotlessExtension

buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.android.tools.build:gradle:7.4.2")
    }
}

plugins {
    // trick: for the same plugin versions in all sub-modules
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library").version("7.4.2").apply(false)
    id("org.jlleitschuh.gradle.ktlint") version Version.ktlint
    kotlin("android").version("1.8.21").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
    id("org.jetbrains.kotlin.jvm") version ("1.8.21") apply false
    kotlin("plugin.serialization").version("1.8.21")
    id("com.diffplug.spotless") version "6.17.0"
    id("jacoco")
    id("dev.icerock.moko.kswift") version "0.6.1"
    id("com.chromaticnoise.multiplatform-swiftpackage") version "2.0.3"
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "com.diffplug.spotless")
    apply(plugin = "jacoco")
    configure<SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            ktlint().setEditorConfigPath(
                "${project.rootDir}/.editorconfig"
            )
            trimTrailingWhitespace()
            endWithNewline()
        }
        kotlinGradle {
            target("*.gradle.kts")
            ktlint().setEditorConfigPath(
                "${project.rootDir}/.editorconfig"
            )
            trimTrailingWhitespace()
            endWithNewline()
        }
        format("xml") {
            target("**/*.xml")
        }
    }

    ktlint {
        debug.set(false)
        verbose.set(true)
        version.set("0.37.2")
        enableExperimentalRules.set(true)
        outputToConsole.set(true)
        ignoreFailures.set(false)
        enableExperimentalRules.set(false)
        additionalEditorconfigFile.set(file("$rootDir/.editorconfig"))
        filter {
            exclude { it.file.path.contains("build/") }
        }
    }

    tasks.withType<Test> {
        configure<JacocoTaskExtension> {
            isIncludeNoLocationClasses = false
        }
    }

    tasks.register<JacocoReport>("jacocoTestReport") {
        group = "Reporting"
        description = "Generate Jacoco coverage reports after running tests."

        classDirectories.setFrom(
            fileTree(
                "$buildDir/tmp/kotlin-classes/debug"
            ) {
                setIncludes(
                    setOf("**/**/*.class", "**/**/*.class")
                )
            }
        )
        sourceDirectories.setFrom(
            files(
                arrayOf("src/commonMain", "src/androidMain")
            )
        )
        executionData.setFrom(
            fileTree(project.projectDir) {
                setIncludes(setOf("**/**/*.exec", "**/**/*.ec"))
            }
        )

        reports {
            xml.required.set(true)
            html.required.set(true)

            xml.outputLocation.set(file("$buildDir/reports/jacoco.xml"))
            html.outputLocation.set(file("$buildDir/reports/jacoco"))
        }
    }
}

tasks.register<Copy>("installGitHooks") {
    val isWindowsOS = org.gradle.internal.os.OperatingSystem.current().isWindows
    val fromDir = "${rootProject.rootDir}/tools/hooks"
    val toDir = "${rootProject.rootDir}/.git/hooks"

    from(fromDir)
    into(toDir)

    onlyIf { !isWindowsOS }
    doLast {
        Runtime.getRuntime().exec("chmod a+x $toDir")
    }
}
afterEvaluate {
    tasks["clean"].dependsOn("installGitHooks")
}

fun tools(propsFile: String): File {
    val tools = file("tools/hooks")
    return File(tools, propsFile)
}

jacoco {
    version = "0.8.9"
}
