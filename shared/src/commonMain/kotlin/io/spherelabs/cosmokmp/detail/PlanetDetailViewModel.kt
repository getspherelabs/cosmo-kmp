package io.spherelabs.cosmokmp.detail

import io.spherelabs.detailpresentation.planet.PlanetDetailEffect
import io.spherelabs.detailpresentation.planet.PlanetDetailState
import io.spherelabs.detailpresentation.planet.PlanetDetailWish
import io.spherelabs.detailpresentation.planet.PlanetMiddleware
import io.spherelabs.detailpresentation.planet.PlanetReducer
import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor

class PlanetDetailViewModel(
    private val planetDetailReducer: PlanetReducer,
    private val planetDetailMiddleware: PlanetMiddleware
) :
    CommonViewModel<PlanetDetailState, PlanetDetailWish, PlanetDetailEffect>() {

    override val store: Store<PlanetDetailState, PlanetDetailWish, PlanetDetailEffect> =
        createMeteor(
            configs = MeteorConfigs.build {
                initialState = PlanetDetailState.Empty
                storeName = PLANET_STORE_NAME
                reducer = planetDetailReducer
                middlewares = listOf(planetDetailMiddleware)
            }
        )

    companion object {
        private const val PLANET_STORE_NAME = "PlanetDetailViewModel"
    }
}
