package io.spherelabs.detailpresentation.article

import io.spherelabs.detaildomain.DeleteFavourite
import io.spherelabs.detaildomain.FetchArticleByUuid
import io.spherelabs.detaildomain.InsertFavourite
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart

class ArticleDetailMiddleware(
    private val fetchArticleByUuid: FetchArticleByUuid,
    private val insertFavourite: InsertFavourite,
    private val deleteFavourite: DeleteFavourite
) : Middleware<ArticleDetailState, ArticleDetailWish> {

    override suspend fun process(
        state: ArticleDetailState,
        wish: ArticleDetailWish,
        next: suspend (ArticleDetailWish) -> Unit
    ) {
        when (wish) {
            is ArticleDetailWish.GetArticleByUuid -> {
                fetchArticleByUuid.execute(wish.uuid).onStart {
                    next.invoke(ArticleDetailWish.GetArticleLoading)
                }.collectLatest { result ->
                    result.onSuccess {
                        next.invoke(ArticleDetailWish.GetArticle(it))
                    }.onFailure {
                        val failureMessage = it.message ?: "Error is occurred."
                        next.invoke(ArticleDetailWish.GetArticleFailed(failureMessage))
                    }
                }
            }
            is ArticleDetailWish.InsertFavourite -> {
                runCatching {
                    insertFavourite.execute(wish.favourite)
                }.onSuccess {
                    next.invoke(ArticleDetailWish.OnFavouriteChanged(true))
                }.onFailure {
                    val failureMessage = it.message ?: "Error is occurred."
                    next.invoke(ArticleDetailWish.InsertFailed(failureMessage))
                }
            }
            is ArticleDetailWish.DeleteFavourite -> {
                runCatching {
                    deleteFavourite.execute(wish.id)
                }.onSuccess {
                    next.invoke(ArticleDetailWish.OnFavouriteChanged(false))
                }.onFailure {
                    val failureMessage = it.message ?: "Error is occurred."
                    next.invoke(ArticleDetailWish.DeleteFailed(failureMessage))
                }
            }
            else -> {}
        }
    }
}
