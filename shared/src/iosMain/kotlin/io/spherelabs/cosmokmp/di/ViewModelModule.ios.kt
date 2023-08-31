package io.spherelabs.cosmokmp.di

import io.spherelabs.cosmokmp.about.AboutViewModel
import io.spherelabs.cosmokmp.detail.ArticleDetailViewModel
import io.spherelabs.cosmokmp.detail.PlanetDetailViewModel
import io.spherelabs.cosmokmp.detail.StarDetailViewModel
import io.spherelabs.cosmokmp.discover.DiscoverViewModel
import io.spherelabs.cosmokmp.favourite.FavouriteViewModel
import io.spherelabs.cosmokmp.onboarding.OnboardingViewModel
import io.spherelabs.cosmokmp.search.SearchViewModel
import io.spherelabs.cosmokmp.stars.StarsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual val viewModelModule: Module = module {
    factoryOf(::OnboardingViewModel)
    factoryOf(::DiscoverViewModel)
    factoryOf(::StarsViewModel)
    factoryOf(::SearchViewModel)
    factoryOf(::FavouriteViewModel)
    factoryOf(::PlanetDetailViewModel)
    factoryOf(::StarDetailViewModel)
    factoryOf(::ArticleDetailViewModel)
    factoryOf(::AboutViewModel)
}
