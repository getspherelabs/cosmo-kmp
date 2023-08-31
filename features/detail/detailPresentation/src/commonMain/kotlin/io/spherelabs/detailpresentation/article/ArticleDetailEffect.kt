package io.spherelabs.detailpresentation.article

sealed interface ArticleDetailEffect {
    data class Failure(val message: String) : ArticleDetailEffect
}
