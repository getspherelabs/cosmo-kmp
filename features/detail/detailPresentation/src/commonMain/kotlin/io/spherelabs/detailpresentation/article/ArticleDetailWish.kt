package io.spherelabs.detailpresentation.article

import io.spherelabs.detaildomain.ArticleDetailDomain
import io.spherelabs.detaildomain.FavouriteDetailDomain

sealed interface ArticleDetailWish {
    object GetArticleLoading : ArticleDetailWish
    data class GetArticleByUuid(val uuid: String) : ArticleDetailWish
    data class GetArticle(val article: ArticleDetailDomain) : ArticleDetailWish
    data class GetArticleFailed(val message: String) : ArticleDetailWish
    data class InsertFavourite(val favourite: FavouriteDetailDomain) : ArticleDetailWish
    data class OnFavouriteChanged(val isFavourite: Boolean) : ArticleDetailWish
    data class InsertFailed(val message: String) : ArticleDetailWish
    data class DeleteFavourite(val id: String) : ArticleDetailWish
    data class DeleteFailed(val message: String) : ArticleDetailWish
}
