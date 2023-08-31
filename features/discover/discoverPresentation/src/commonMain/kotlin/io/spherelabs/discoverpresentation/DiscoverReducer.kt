package io.spherelabs.discoverpresentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class DiscoverReducer : Reducer<DiscoverState, DiscoverWish, DiscoverEffect> {
    override fun reduce(
        state: DiscoverState,
        wish: DiscoverWish
    ): Change<DiscoverState, DiscoverEffect> {
        return when (wish) {
            is DiscoverWish.ArticleFailed -> {
                expect(
                    effectAction = {
                        DiscoverEffect.Failure(wish.message)
                    },
                    stateAction = {
                        state.copy(
                            isArticleLoading = false
                        )
                    }
                )
            }
            DiscoverWish.ArticleLoading -> {
                expect {
                    state.copy(
                        isArticleLoading = true
                    )
                }
            }
            is DiscoverWish.GetArticle -> {
                expect {
                    state.copy(
                        article = wish.article,
                        isArticleLoading = false
                    )
                }
            }
            is DiscoverWish.GetPopularPlanets -> {
                expect {
                    state.copy(
                        popularPlanets = wish.planets,
                        isLoading = false
                    )
                }
            }
            is DiscoverWish.PopularPlanetsFailed -> {
                expect(
                    effectAction = {
                        DiscoverEffect.Failure(wish.message)
                    },
                    stateAction = {
                        state.copy(
                            isLoading = false
                        )
                    }
                )
            }
            DiscoverWish.PopularPlanetsLoading -> {
                expect {
                    state.copy(
                        isLoading = true
                    )
                }
            }
            else -> {
                unexpected { state }
            }
        }
    }
}
