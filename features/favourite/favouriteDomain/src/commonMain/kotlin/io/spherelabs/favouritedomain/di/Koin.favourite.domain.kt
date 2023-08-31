package io.spherelabs.favouritedomain.di

import io.spherelabs.favouritedomain.DefaultDeleteFavourite
import io.spherelabs.favouritedomain.DefaultGetFavourites
import io.spherelabs.favouritedomain.DefaultInsertFavourite
import io.spherelabs.favouritedomain.DeleteFavourite
import io.spherelabs.favouritedomain.GetFavourites
import io.spherelabs.favouritedomain.InsertFavourite
import org.koin.dsl.module

val favouriteDomainModule = module {
    single<InsertFavourite> { DefaultInsertFavourite(get()) }
    single<GetFavourites> { DefaultGetFavourites(get()) }
    single<DeleteFavourite> { DefaultDeleteFavourite(get()) }
}
