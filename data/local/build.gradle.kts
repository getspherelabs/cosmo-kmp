plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("app.cash.sqldelight") version "2.0.0-rc02"
    jacoco
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
            baseName = "local"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libs.Koin.core)
                implementation(Libs.SqlDelight.runtime)
                implementation(Libs.SqlDelight.extension)
                implementation(Libs.SqlDelight.primitiveAdapter)
                implementation(project(":features:favourite:favouriteDomain"))
                implementation(project(":features:detail:detailDomain"))
                implementation(project(":features:about:aboutDomain"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(Libs.Testing.turbine)
                implementation(Libs.Testing.coroutine)
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libs.SqlDelight.android)
                implementation(Libs.Koin.android)
            }
        }
        val iosMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libs.SqlDelight.native)
            }
        }
        val androidUnitTest by getting {
            dependsOn(commonTest)
            dependencies {
                implementation(Libs.SqlDelight.test)
            }
        }
        val iosTest by getting {
            dependsOn(commonTest)
            dependencies {
                implementation(Libs.SqlDelight.native)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}

android {
    namespace = "io.spherelabs.local"
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

sqldelight {
    databases {
        create("CosmoDatabase") {
            packageName.set("io.spherelabs.local.db")
        }
    }
}
