package io.spherelabs.setting.di

import com.russhwolf.settings.ExperimentalSettingsApi
import io.spherelabs.setting.onboarding.DefaultOnboardingSetting
import io.spherelabs.setting.onboarding.OnboardingSetting
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

@OptIn(ExperimentalSettingsApi::class)
val settingModule = module {
    includes(platformModule())
    single<OnboardingSetting> { DefaultOnboardingSetting(get()) }
}
