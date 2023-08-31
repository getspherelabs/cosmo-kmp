package io.spherelabs.detaildomain

data class ArticleDetailDomain(
    val id: String,
    val title: String,
    val description: String,
    val author: String,
    val createdTimestamp: Long,
    val updatedTimestamp: Long
)
