package io.spherelabs.searchpresentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class SearchReducer : Reducer<SearchState, SearchWish, SearchEffect> {

    override fun reduce(state: SearchState, wish: SearchWish): Change<SearchState, SearchEffect> {
        return when (wish) {
            is SearchWish.GetPlanets -> {
                expect { state.copy(planets = wish.planets, isLoading = false) }
            }

            is SearchWish.SearchFailed -> {
                expect(
                    effectAction = {
                        SearchEffect.Failure(wish.message)
                    },
                    stateAction = {
                        state.copy(isLoading = false, planets = emptyList())
                    }
                )
            }

            SearchWish.SearchLoading -> {
                expect { state.copy(isLoading = true) }
            }

            else -> unexpected { state }
        }
    }
}
