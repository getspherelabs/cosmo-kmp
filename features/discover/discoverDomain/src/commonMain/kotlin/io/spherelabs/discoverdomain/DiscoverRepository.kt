package io.spherelabs.discoverdomain

interface DiscoverRepository {
    suspend fun fetchArticles(): ArticlesDomain
    suspend fun fetchPopularPlanets(): PlanetsDomain
}
