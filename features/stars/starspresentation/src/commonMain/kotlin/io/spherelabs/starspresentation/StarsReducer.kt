package io.spherelabs.starspresentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class StarsReducer : Reducer<StarsState, StarsWish, StarsEffect> {

    override fun reduce(state: StarsState, wish: StarsWish): Change<StarsState, StarsEffect> {
        return when (wish) {
            is StarsWish.GetStars -> {
                expect {
                    state.copy(stars = wish.list, isLoading = false)
                }
            }

            is StarsWish.StarsFailed -> {
                expect(
                    effectAction = {
                        StarsEffect.Failure(wish.message)
                    },
                    stateAction = {
                        state.copy(isLoading = false)
                    }
                )
            }

            StarsWish.StarsLoading -> {
                expect { state.copy(isLoading = true) }
            }

            else -> {
                unexpected { state }
            }
        }
    }
}
