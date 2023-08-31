package io.spherelabs.detaildomain.di

import io.spherelabs.detaildomain.DefaultDeleteFavourite
import io.spherelabs.detaildomain.DefaultFetchArticleByUuid
import io.spherelabs.detaildomain.DefaultFetchPlanetByUuid
import io.spherelabs.detaildomain.DefaultFetchStarByUuid
import io.spherelabs.detaildomain.DefaultHasFavourite
import io.spherelabs.detaildomain.DefaultInsertFavourite
import io.spherelabs.detaildomain.DeleteFavourite
import io.spherelabs.detaildomain.FetchArticleByUuid
import io.spherelabs.detaildomain.FetchPlanetByUuid
import io.spherelabs.detaildomain.FetchStarByUuid
import io.spherelabs.detaildomain.HasFavourite
import io.spherelabs.detaildomain.InsertFavourite
import org.koin.dsl.module

val detailDomainModule = module {
    single<InsertFavourite> { DefaultInsertFavourite(get()) }
    single<DeleteFavourite> { DefaultDeleteFavourite(get()) }
    single<HasFavourite> { DefaultHasFavourite(get()) }
    single<FetchStarByUuid> { DefaultFetchStarByUuid(get()) }
    single<FetchPlanetByUuid> { DefaultFetchPlanetByUuid(get()) }
    single<FetchArticleByUuid> { DefaultFetchArticleByUuid(get()) }
}
