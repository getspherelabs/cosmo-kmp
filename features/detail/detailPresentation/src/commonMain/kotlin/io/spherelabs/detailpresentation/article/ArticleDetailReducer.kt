package io.spherelabs.detailpresentation.article

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class ArticleDetailReducer : Reducer<ArticleDetailState, ArticleDetailWish, ArticleDetailEffect> {

    override fun reduce(
        state: ArticleDetailState,
        wish: ArticleDetailWish
    ): Change<ArticleDetailState, ArticleDetailEffect> {
        return when (wish) {
            is ArticleDetailWish.GetArticle -> {
                expect {
                    state.copy(isLoading = false, article = wish.article)
                }
            }
            is ArticleDetailWish.GetArticleFailed -> {
                expect(
                    effectAction = { ArticleDetailEffect.Failure(wish.message) },
                    stateAction = { state.copy(isLoading = false) }
                )
            }
            ArticleDetailWish.GetArticleLoading -> {
                expect { state.copy(isLoading = true) }
            }
            is ArticleDetailWish.InsertFailed -> {
                effect { ArticleDetailEffect.Failure(wish.message) }
            }
            is ArticleDetailWish.OnFavouriteChanged -> {
                expect { state.copy(isFavourite = wish.isFavourite) }
            }
            is ArticleDetailWish.DeleteFailed -> {
                effect { ArticleDetailEffect.Failure(wish.message) }
            }
            else -> unexpected { state }
        }
    }
}
