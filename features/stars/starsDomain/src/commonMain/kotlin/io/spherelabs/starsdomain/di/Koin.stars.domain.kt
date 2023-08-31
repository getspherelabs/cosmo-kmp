package io.spherelabs.starsdomain.di

import io.spherelabs.starsdomain.DefaultFetchStarByUuid
import io.spherelabs.starsdomain.DefaultFetchStars
import io.spherelabs.starsdomain.FetchStarByUuid
import io.spherelabs.starsdomain.FetchStars
import org.koin.dsl.module

val starsDomainModule = module {
    single<FetchStars> { DefaultFetchStars(get()) }
    single<FetchStarByUuid> { DefaultFetchStarByUuid(get()) }
}
