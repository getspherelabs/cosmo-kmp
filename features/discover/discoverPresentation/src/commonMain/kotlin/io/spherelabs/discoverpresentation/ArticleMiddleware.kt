package io.spherelabs.discoverpresentation

import io.spherelabs.discoverdomain.FetchArticles
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart

class ArticleMiddleware(
    private val fetchArticles: FetchArticles
) : Middleware<DiscoverState, DiscoverWish> {

    override suspend fun process(
        state: DiscoverState,
        wish: DiscoverWish,
        next: suspend (DiscoverWish) -> Unit
    ) {
        when (wish) {
            DiscoverWish.ArticleStarted -> {
                fetchArticles.execute()
                    .onStart {
                        next.invoke(DiscoverWish.ArticleLoading)
                    }
                    .collectLatest { result ->
                        result.onSuccess { newArticles ->
                            val newArticle = newArticles.articles.first()
                            next.invoke(DiscoverWish.GetArticle(newArticle))
                        }.onFailure { newException ->
                            val failureMessage = newException.message ?: "Error is occurred"
                            next.invoke(DiscoverWish.ArticleFailed(failureMessage))
                        }
                    }
            }
            else -> {}
        }
    }
}
