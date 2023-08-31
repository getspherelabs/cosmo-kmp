pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "cosmo-kmp"
include(":androidApp")
include(":shared")
include(":data")
include(":data")
include(":data:network")
include(":features")
include(":data:setting")
include(":features:discover")
include(":features:discover:discoverDomain")
include(":features:discover:discoverPresentation")
include(":features:stars")
include(":features:stars:starsDomain")
include(":features:stars:starsPresentation")
include(":features:search")
include(":features:search:searchDomain")
include(":features:search:searchPresentation")
include(":data:local")
include(":features:favourite")
include(":features:favourite:favouriteDomain")
include(":features:favourite:favouritePresentation")
include(":features:detail")
include(":features:detail:detailDomain")
include(":features:detail:detailPresentation")
include(":features:about")
include(":features:about:aboutDomain")
include(":features:about:aboutPresentation")
include(":features:auth")
include(":features:onboarding:onboardingDomain")
include(":features:onboarding:onboardingPresentation")
