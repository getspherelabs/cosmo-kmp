package io.spherelabs.discoverdomain

data class ArticleDomain(
    val id: String,
    val title: String,
    val description: String,
    val author: String,
    val createdTimestamp: Long,
    val updatedTimestamp: Long
)

data class ArticlesDomain(
    val articles: List<ArticleDomain>
)
