package io.spherelabs.favouritepresentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class FavouriteReducer : Reducer<FavouriteState, FavouriteWish, FavouriteEffect> {

    override fun reduce(
        currentState: FavouriteState,
        currentWish: FavouriteWish
    ): Change<FavouriteState, FavouriteEffect> {
        return when (currentWish) {
            is FavouriteWish.GetFailed -> {
                expect(
                    effectAction = {
                        FavouriteEffect.Failure(currentWish.message)
                    },
                    stateAction = {
                        currentState.copy(isLoading = false)
                    }
                )
            }

            is FavouriteWish.GetFavourite -> {
                expect { currentState.copy(favourites = currentWish.favourites, isLoading = false) }
            }

            FavouriteWish.GetLoading -> {
                expect { currentState.copy(isLoading = true) }
            }

            else -> unexpected { currentState }
        }
    }
}
