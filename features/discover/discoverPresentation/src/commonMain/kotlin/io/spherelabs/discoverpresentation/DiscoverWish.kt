package io.spherelabs.discoverpresentation

import io.spherelabs.discoverdomain.ArticleDomain
import io.spherelabs.discoverdomain.PlanetDomain

sealed interface DiscoverWish {
    object ArticleStarted : DiscoverWish
    data class ArticleFailed(val message: String) : DiscoverWish
    data class GetArticle(val article: ArticleDomain) : DiscoverWish
    object ArticleLoading : DiscoverWish
    object PopularPlanetsStarted : DiscoverWish
    data class PopularPlanetsFailed(val message: String) : DiscoverWish
    data class GetPopularPlanets(val planets: List<PlanetDomain>) : DiscoverWish
    object PopularPlanetsLoading : DiscoverWish
}
