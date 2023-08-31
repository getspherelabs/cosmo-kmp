package io.spherelabs.onboardingdomain.di

import io.spherelabs.onboardingdomain.DefaultIsFirstTime
import io.spherelabs.onboardingdomain.DefaultSetIsFirstTime
import io.spherelabs.onboardingdomain.IsFirstTime
import io.spherelabs.onboardingdomain.SetIsFirstTime
import org.koin.dsl.module

val domainModule = module {
    single<SetIsFirstTime> { DefaultSetIsFirstTime(get()) }
    single<IsFirstTime> { DefaultIsFirstTime(get()) }
}
