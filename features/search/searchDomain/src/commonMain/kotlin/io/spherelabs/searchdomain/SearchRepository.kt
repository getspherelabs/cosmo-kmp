package io.spherelabs.searchdomain

interface SearchRepository {
    suspend fun searchPlanetsByName(query: String): SearchPlanetsDomain
}
