plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "io.spherelabs.cosmokmp.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "io.spherelabs.cosmokmp.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":features:onboarding:onboardingDomain"))
    implementation(project(":features:onboarding:onboardingPresentation"))
    implementation(project(":features:discover:discoverPresentation"))
    implementation(project(":features:discover:discoverDomain"))
    implementation(project(":features:stars:starsPresentation"))
    implementation(project(":features:stars:starsDomain"))
    implementation(project(":features:search:searchPresentation"))
    implementation(project(":features:search:searchDomain"))
    implementation(project(":features:favourite:favouritePresentation"))
    implementation(project(":features:favourite:favouriteDomain"))
    implementation(project(":features:detail:detailPresentation"))
    implementation(project(":features:detail:detailDomain"))
    implementation(project(":features:about:aboutDomain"))
    implementation(project(":features:about:aboutPresentation"))

    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.ui:ui-tooling:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0")
    implementation("androidx.compose.foundation:foundation:1.4.0")
    implementation("androidx.compose.material:material:1.4.0")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(Libs.Koin.android)
    implementation(Libs.Koin.compose)
    implementation(Libs.Android.composeLifecycle)
    implementation(Libs.Android.navigation)
    implementation(Libs.Android.navigationAnimation)
    implementation(Libs.Android.composeAnimation)
    implementation(Libs.Firebase.auth)
    implementation(Libs.Android.coil)
}
