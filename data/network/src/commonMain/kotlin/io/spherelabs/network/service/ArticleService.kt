package io.spherelabs.network.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.spherelabs.network.dto.ArticleDto
import io.spherelabs.network.dto.ArticlesDto

interface ArticleService {
    suspend fun fetchArticles(): ArticlesDto
    suspend fun fetchArticleByUuid(uuid: String): ArticleDto
}

class DefaultArticleService(
    private val httpClient: HttpClient,
    val baseUrl: String = "https://cosmoserver-c6319eea23ed.herokuapp.com/"
) : ArticleService {
    override suspend fun fetchArticles(): ArticlesDto {
        return httpClient.get("$baseUrl/api/v1/articles").body()
    }

    override suspend fun fetchArticleByUuid(uuid: String): ArticleDto {
        return httpClient.get("$baseUrl/api/v1/article/$uuid").body()
    }
}
