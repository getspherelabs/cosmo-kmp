package service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.spherelabs.network.dto.PlanetDto
import io.spherelabs.network.dto.PlanetsDto
import io.spherelabs.network.service.PlanetService

class MockPlanetService(
    private val httpClient: HttpClient
) : PlanetService {
    override suspend fun fetchPlanets(): PlanetsDto {
        return httpClient.get("/api/v1/planets").body()
    }

    override suspend fun fetchPlanetById(id: String): PlanetDto {
        return httpClient.get("/api/v1/planet/$id").body()
    }

    override suspend fun fetchPopularPlanets(): PlanetsDto {
        TODO("Not yet implemented")
    }

    override suspend fun searchPlanet(query: String): PlanetsDto {
        TODO("Not yet implemented")
    }
}
