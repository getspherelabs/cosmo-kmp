package io.spherelabs.searchdomain.di

import io.spherelabs.searchdomain.DefaultSearchPlanetsByName
import io.spherelabs.searchdomain.SearchPlanetsByName
import org.koin.dsl.module

val searchDomainModule = module {
    single<SearchPlanetsByName> { DefaultSearchPlanetsByName(get()) }
}
