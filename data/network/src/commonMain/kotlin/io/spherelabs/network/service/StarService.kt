package io.spherelabs.network.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.spherelabs.network.dto.StarDto
import io.spherelabs.network.dto.StarsDto

interface StarService {
    suspend fun fetchStars(): StarsDto
    suspend fun fetchStarByUuid(uuid: String): StarDto
}

class DefaultStarService(
    private val httpClient: HttpClient,
    val baseUrl: String = "https://cosmoserver-c6319eea23ed.herokuapp.com/"
) : StarService {
    override suspend fun fetchStars(): StarsDto {
        return httpClient.get("$baseUrl/api/v1/stars").body()
    }

    override suspend fun fetchStarByUuid(uuid: String): StarDto {
        return httpClient.get("$baseUrl/api/v1/star/$uuid").body()
    }
}
