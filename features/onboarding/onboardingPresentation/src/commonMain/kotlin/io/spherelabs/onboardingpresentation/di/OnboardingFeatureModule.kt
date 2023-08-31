package io.spherelabs.onboardingpresentation.di

import io.spherelabs.onboardingpresentation.OnboardingMiddleware
import io.spherelabs.onboardingpresentation.OnboardingReducer
import org.koin.dsl.module

val onboardingFeatureModule = module {
    single { OnboardingReducer() }
    single { OnboardingMiddleware(get(), get()) }
}
