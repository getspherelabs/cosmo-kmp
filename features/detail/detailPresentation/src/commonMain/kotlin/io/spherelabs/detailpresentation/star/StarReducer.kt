package io.spherelabs.detailpresentation.star

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class StarReducer : Reducer<StarDetailState, StarDetailWish, StarDetailEffect> {

    override fun reduce(
        state: StarDetailState,
        wish: StarDetailWish
    ): Change<StarDetailState, StarDetailEffect> {
        return when (wish) {
            is StarDetailWish.GetStar -> {
                expect { state.copy(star = wish.star, isLoading = false) }
            }
            is StarDetailWish.GetStarByUuidFailed -> {
                expect(
                    effectAction = {
                        StarDetailEffect.Failure(wish.message)
                    },
                    stateAction = {
                        state.copy(isLoading = false)
                    }
                )
            }
            StarDetailWish.GetStarByUuidLoading -> {
                expect { state.copy(isLoading = true) }
            }
            is StarDetailWish.InsertFailed -> {
                effect { StarDetailEffect.Failure(wish.message) }
            }
            is StarDetailWish.OnFavouriteChanged -> {
                expect { state.copy(isFavourite = wish.isFavourite) }
            }
            else -> unexpected { state }
        }
    }
}
