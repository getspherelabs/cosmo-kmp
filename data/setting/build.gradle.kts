plugins {
    kotlin("multiplatform")
    id("com.android.library")
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
            baseName = "setting"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(Libs.Settings.coroutine)
                api(Libs.Settings.core)
                api(Libs.Koin.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libs.Koin.android)
                implementation(Libs.Android.datastore)
                implementation(Libs.Android.datastorePref)
                api(Libs.Settings.datastore)
            }
        }

        val iosMain by getting {
            dependsOn(commonMain)
        }
    }
}

android {
    namespace = "io.spherelabs.setting"
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
