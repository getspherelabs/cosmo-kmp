package io.spherelabs.cosmokmp.di

import io.spherelabs.aboutdomain.di.aboutDomainModule
import io.spherelabs.aboutpresentation.AboutMiddleware
import io.spherelabs.aboutpresentation.AboutReducer
import io.spherelabs.detaildomain.di.detailDomainModule
import io.spherelabs.detailpresentation.article.ArticleDetailMiddleware
import io.spherelabs.detailpresentation.article.ArticleDetailReducer
import io.spherelabs.detailpresentation.planet.PlanetMiddleware
import io.spherelabs.detailpresentation.planet.PlanetReducer
import io.spherelabs.detailpresentation.star.StarMiddleware
import io.spherelabs.detailpresentation.star.StarReducer
import io.spherelabs.discoverdomain.di.discoverDomainModule
import io.spherelabs.discoverpresentation.ArticleMiddleware
import io.spherelabs.discoverpresentation.DiscoverReducer
import io.spherelabs.discoverpresentation.PopularPlanetsMiddleware
import io.spherelabs.favouritedomain.di.favouriteDomainModule
import io.spherelabs.favouritepresentation.FavouriteMiddleware
import io.spherelabs.favouritepresentation.FavouriteReducer
import io.spherelabs.local.di.localModule
import io.spherelabs.network.di.serviceModule
import io.spherelabs.onboardingdomain.di.domainModule
import io.spherelabs.onboardingpresentation.di.onboardingFeatureModule
import io.spherelabs.searchdomain.di.searchDomainModule
import io.spherelabs.searchpresentation.di.searchFeatureModule
import io.spherelabs.setting.di.settingModule
import io.spherelabs.starsdomain.di.starsDomainModule
import io.spherelabs.starspresentation.di.starsFeatureModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(declaration: KoinAppDeclaration = {}) =
    startKoin {
        declaration()

        modules(
            serviceModule,
            localModule,
            settingModule,
            domainModule,
            onboardingFeatureModule,
            discoverFeatureModule,
            discoverDomainModule,
            starsDomainModule,
            starsFeatureModule,
            searchDomainModule,
            searchFeatureModule,
            favouriteDomainModule,
            favouriteFeatureModule,
            detailDomainModule,
            detailFeatureModule,
            aboutDomainModule,
            aboutFeatureModule,
            viewModelModule
        )
    }

fun initKoin() = initKoin() {}

val discoverFeatureModule = module {
    single { DiscoverReducer() }
    single { ArticleMiddleware(get()) }
    single { PopularPlanetsMiddleware(get()) }
}

val favouriteFeatureModule = module {
    single { FavouriteReducer() }
    single { FavouriteMiddleware(get()) }
}

val detailFeatureModule = module {
    single { PlanetReducer() }
    single { StarReducer() }
    single { ArticleDetailReducer() }
    single { StarMiddleware(get(), get(), get()) }
    single { PlanetMiddleware(get(), get(), get()) }
    single { ArticleDetailMiddleware(get(), get(), get()) }
}

val aboutFeatureModule = module {
    single { AboutReducer() }
    single { AboutMiddleware(get(), get(), get()) }
}
