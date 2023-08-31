package io.spherelabs.detailpresentation.article

import io.spherelabs.detaildomain.ArticleDetailDomain

data class ArticleDetailState(
    val article: ArticleDetailDomain? = null,
    val isLoading: Boolean = false,
    val isFavourite: Boolean = false
) {
    companion object {
        val Empty = ArticleDetailState()
    }
}
