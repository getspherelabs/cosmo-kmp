plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("dev.icerock.moko.kswift")
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
            baseName = "shared"
            linkerOpts.add("-lsqlite3")
            export(Libs.Meteor.viewModel)
            export(Libs.Meteor.core)
            export(Libs.Coroutine.core)
            export(project(":features:onboarding:onboardingPresentation"))
            export(project(":features:search:searchPresentation"))
            export(project(":features:stars:starsPresentation"))
            export(project(":features:favourite:favouritePresentation"))
            export(project(":features:about:aboutPresentation"))
            export(project(":features:discover:discoverPresentation"))
            export(project(":features:detail:detailPresentation"))
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(Libs.Meteor.viewModel)
                api(Libs.Meteor.core)
                api(Libs.Coroutine.core)
                api(Libs.Koin.core)

                api(project(":data:setting"))
                api(project(":data:network"))
                api(project(":data:local"))

                api(project(":features:about:aboutPresentation"))
                api(project(":features:about:aboutDomain"))

                api(project(":features:discover:discoverDomain"))
                api(project(":features:discover:discoverPresentation"))

                api(project(":features:stars:starsDomain"))
                api(project(":features:stars:starsPresentation"))

                api(project(":features:search:searchDomain"))
                api(project(":features:search:searchPresentation"))

                api(project(":features:favourite:favouriteDomain"))
                api(project(":features:favourite:favouritePresentation"))

                api(project(":features:detail:detailDomain"))
                api(project(":features:detail:detailPresentation"))

                api(project(":features:onboarding:onboardingDomain"))
                api(project(":features:onboarding:onboardingPresentation"))
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
                implementation(Libs.Android.viewModel)
                api(Libs.Koin.android)
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                api(Libs.Coroutine.core)
            }
        }
    }
}

android {
    namespace = "io.spherelabs.cosmokmp"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

kswift {
    install(dev.icerock.moko.kswift.plugin.feature.SealedToSwiftEnumFeature)
}
