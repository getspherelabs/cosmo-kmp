package io.spherelabs.searchpresentation.di

import io.spherelabs.searchpresentation.SearchMiddleware
import io.spherelabs.searchpresentation.SearchReducer
import org.koin.dsl.module

val searchFeatureModule = module {
    single { SearchReducer() }
    single { SearchMiddleware(get()) }
}
