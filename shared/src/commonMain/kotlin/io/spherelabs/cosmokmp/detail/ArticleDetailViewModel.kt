package io.spherelabs.cosmokmp.detail

import io.spherelabs.detailpresentation.article.ArticleDetailEffect
import io.spherelabs.detailpresentation.article.ArticleDetailMiddleware
import io.spherelabs.detailpresentation.article.ArticleDetailReducer
import io.spherelabs.detailpresentation.article.ArticleDetailState
import io.spherelabs.detailpresentation.article.ArticleDetailWish
import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor

class ArticleDetailViewModel(
    private val articleReducer: ArticleDetailReducer,
    private val articleMiddleware: ArticleDetailMiddleware
) : CommonViewModel<ArticleDetailState, ArticleDetailWish, ArticleDetailEffect>() {

    override val store: Store<ArticleDetailState, ArticleDetailWish, ArticleDetailEffect> =
        createMeteor(
            configs = MeteorConfigs.build {
                initialState = ArticleDetailState.Empty
                storeName = ARTICLE_STORE_NAME
                reducer = articleReducer
                middlewares = listOf(articleMiddleware)
            }
        )

    companion object {
        private const val ARTICLE_STORE_NAME = "ArticleDetailViewModel"
    }
}
