package io.spherelabs.network.repository

import io.spherelabs.network.dto.asSearchDomain
import io.spherelabs.network.service.PlanetService
import io.spherelabs.searchdomain.SearchPlanetsDomain
import io.spherelabs.searchdomain.SearchRepository

class DefaultSearchRepository(
    private val planetService: PlanetService
) : SearchRepository {

    override suspend fun searchPlanetsByName(query: String): SearchPlanetsDomain {
        return planetService.searchPlanet(query).asSearchDomain()
    }
}
