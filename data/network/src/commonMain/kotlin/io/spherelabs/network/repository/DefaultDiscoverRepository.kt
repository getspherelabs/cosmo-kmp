package io.spherelabs.network.repository

import io.spherelabs.discoverdomain.ArticlesDomain
import io.spherelabs.discoverdomain.DiscoverRepository
import io.spherelabs.discoverdomain.PlanetsDomain
import io.spherelabs.network.dto.asDomain
import io.spherelabs.network.service.ArticleService
import io.spherelabs.network.service.PlanetService

class DefaultDiscoverRepository(
    private val planetService: PlanetService,
    private val articleService: ArticleService
) : DiscoverRepository {

    override suspend fun fetchArticles(): ArticlesDomain {
        return articleService.fetchArticles().asDomain()
    }

    override suspend fun fetchPopularPlanets(): PlanetsDomain {
        return planetService.fetchPopularPlanets().asDomain()
    }
}
