package io.spherelabs.local.di

import io.spherelabs.local.db.DriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DriverFactory() }
}
