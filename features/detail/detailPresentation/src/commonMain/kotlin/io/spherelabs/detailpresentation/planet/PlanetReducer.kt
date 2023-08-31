package io.spherelabs.detailpresentation.planet

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class PlanetReducer : Reducer<PlanetDetailState, PlanetDetailWish, PlanetDetailEffect> {

    override fun reduce(
        state: PlanetDetailState,
        wish: PlanetDetailWish
    ): Change<PlanetDetailState, PlanetDetailEffect> {
        return when (wish) {
            is PlanetDetailWish.GetPlanet -> {
                expect { state.copy(planet = wish.planet, isLoading = false) }
            }
            is PlanetDetailWish.GetPlanetByUuidFailed -> {
                expect(
                    effectAction = {
                        PlanetDetailEffect.Failure(wish.message)
                    },
                    stateAction = {
                        state.copy(isLoading = false)
                    }
                )
            }
            PlanetDetailWish.GetPlanetByUuidLoading -> {
                expect { state.copy(isLoading = true) }
            }
            is PlanetDetailWish.InsertFailed -> {
                effect { PlanetDetailEffect.Failure(wish.message) }
            }
            is PlanetDetailWish.OnFavouriteChanged -> {
                expect { state.copy(isFavourite = wish.isFavourite) }
            }
            else -> unexpected { state }
        }
    }
}
