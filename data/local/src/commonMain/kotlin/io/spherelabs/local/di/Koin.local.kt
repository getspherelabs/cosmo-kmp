package io.spherelabs.local.di

import io.spherelabs.aboutdomain.AboutRepository
import io.spherelabs.favouritedomain.FavouriteRepository
import io.spherelabs.local.db.AboutDao
import io.spherelabs.local.db.DefaultAboutDao
import io.spherelabs.local.db.DefaultFavouriteDao
import io.spherelabs.local.db.FavouriteDao
import io.spherelabs.local.db.createDatabase
import io.spherelabs.local.repository.DefaultAboutRepository
import io.spherelabs.local.repository.DefaultFavouriteRepository
import org.koin.dsl.module

val localModule = module {
    includes(platformModule())
    factory { createDatabase(get()) }
    single<FavouriteDao> { DefaultFavouriteDao(get()) }
    single<AboutDao> { DefaultAboutDao(get()) }
    single<FavouriteRepository> { DefaultFavouriteRepository(get()) }
    single<AboutRepository> { DefaultAboutRepository(get()) }
}
