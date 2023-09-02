package io.spherelabs.starspresentation.di

import io.spherelabs.starspresentation.StarsMiddleware
import io.spherelabs.starspresentation.StarsReducer
import org.koin.dsl.module

val starsFeatureModule = module {
    single { StarsReducer() }
    single { StarsMiddleware(get()) }
}
