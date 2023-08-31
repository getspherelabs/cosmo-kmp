package io.spherelabs.cosmokmp.stars

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor
import io.spherelabs.starspresentation.StarsEffect
import io.spherelabs.starspresentation.StarsMiddleware
import io.spherelabs.starspresentation.StarsReducer
import io.spherelabs.starspresentation.StarsState
import io.spherelabs.starspresentation.StarsWish

class StarsViewModel(
    private val starsReducer: StarsReducer,
    private val starsMiddleware: StarsMiddleware
) : CommonViewModel<StarsState, StarsWish, StarsEffect>() {

    override val store: Store<StarsState, StarsWish, StarsEffect> = createMeteor(
        configs = MeteorConfigs.build {
            initialState = StarsState.Empty
            storeName = "StarsViewModel"
            reducer = starsReducer
            middlewares = listOf(starsMiddleware)
        }
    )
}
