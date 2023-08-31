package io.spherelabs.discoverdomain.di

import io.spherelabs.discoverdomain.DefaultFetchArticles
import io.spherelabs.discoverdomain.DefaultFetchPopularPlanets
import io.spherelabs.discoverdomain.FetchArticles
import io.spherelabs.discoverdomain.FetchPopularPlanets
import org.koin.dsl.module

val discoverDomainModule = module {
    single<FetchArticles> { DefaultFetchArticles(get()) }
    single<FetchPopularPlanets> { DefaultFetchPopularPlanets(get()) }
}
