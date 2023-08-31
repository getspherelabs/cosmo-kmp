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
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val viewModelModule = module {
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::DiscoverViewModel)
    viewModelOf(::StarsViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::FavouriteViewModel)
    viewModelOf(::PlanetDetailViewModel)
    viewModelOf(::StarDetailViewModel)
    viewModelOf(::ArticleDetailViewModel)
    viewModelOf(::AboutViewModel)
}
