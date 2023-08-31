package io.spherelabs.network.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.spherelabs.network.dto.PlanetDto
import io.spherelabs.network.dto.PlanetsDto

interface PlanetService {
    suspend fun fetchPlanets(): PlanetsDto
    suspend fun fetchPlanetById(id: String): PlanetDto
    suspend fun fetchPopularPlanets(): PlanetsDto
    suspend fun searchPlanet(query: String): PlanetsDto
}

class DefaultPlanetService(
    private val httpClient: HttpClient,
    val baseUrl: String = "https://cosmoserver-c6319eea23ed.herokuapp.com/"
) : PlanetService {
    override suspend fun fetchPlanets(): PlanetsDto {
        return httpClient.get("$baseUrl/api/v1/planets").body()
    }

    override suspend fun fetchPlanetById(id: String): PlanetDto {
        return httpClient.get("$baseUrl/api/v1/planet/$id").body()
    }

    override suspend fun fetchPopularPlanets(): PlanetsDto {
        return httpClient.get("$baseUrl/api/v1/planets/popular").body()
    }

    override suspend fun searchPlanet(query: String): PlanetsDto {
        return httpClient.get("$baseUrl/api/v1/planets/$query").body()
    }
}
