package io.spherelabs.network.dto

import io.spherelabs.detaildomain.ArticleDetailDomain
import io.spherelabs.discoverdomain.ArticleDomain
import io.spherelabs.discoverdomain.ArticlesDomain

@kotlinx.serialization.Serializable
data class ArticleDto(
    val id: String,
    val title: String,
    val description: String,
    val author: String,
    val createdTimestamp: Long,
    val updatedTimestamp: Long
)

fun ArticleDto.asDomain(): ArticleDomain {
    return ArticleDomain(
        id = id,
        title = title,
        description = description,
        author = author,
        createdTimestamp = createdTimestamp,
        updatedTimestamp = updatedTimestamp
    )
}

fun ArticleDto.asDetailDomain(): ArticleDetailDomain {
    return ArticleDetailDomain(
        id = id,
        title = title,
        description = description,
        author = author,
        createdTimestamp = createdTimestamp,
        updatedTimestamp = updatedTimestamp
    )
}

@kotlinx.serialization.Serializable
data class ArticlesDto(
    val articles: List<ArticleDto>
)

fun ArticlesDto.asDomain(): ArticlesDomain {
    return ArticlesDomain(articles = this.articles.map { it.asDomain() })
}
