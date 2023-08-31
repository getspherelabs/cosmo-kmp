plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
}
@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "network"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libs.Koin.core)
                implementation(Libs.Ktor.core)
                implementation(Libs.Ktor.content)
                implementation(Libs.Ktor.serializationJson)
                implementation(Libs.Ktor.logging)
                implementation(project(":features:discover:discoverDomain"))
                implementation(project(":features:stars:starsDomain"))
                implementation(project(":features:search:searchDomain"))
                implementation(project(":features:detail:detailDomain"))
                implementation(project(":data:local"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(Libs.Ktor.test)
                implementation(Libs.Ktor.mock)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libs.Ktor.okhttp)
            }
        }

        val iosMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libs.Ktor.darwin)
            }
        }
    }
}

android {
    namespace = "io.spherelabs.network"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.all { test ->
            test.testLogging.showStandardStreams = true
            jacoco.jacocoVersion = "0.8.9"
            test.extensions.configure<JacocoTaskExtension> {
                isIncludeNoLocationClasses = true
                excludes = listOf("jdk.internal.*")
            }
        }
    }
    buildTypes.getByName("debug") {
        enableUnitTestCoverage = true
        enableAndroidTestCoverage = true
    }
}
