package io.spherelabs.cosmokmp.favourite

import io.spherelabs.favouritepresentation.FavouriteEffect
import io.spherelabs.favouritepresentation.FavouriteMiddleware
import io.spherelabs.favouritepresentation.FavouriteReducer
import io.spherelabs.favouritepresentation.FavouriteState
import io.spherelabs.favouritepresentation.FavouriteWish
import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor

class FavouriteViewModel(
    private val favouriteReducer: FavouriteReducer,
    private val favouriteMiddleware: FavouriteMiddleware
) : CommonViewModel<FavouriteState, FavouriteWish, FavouriteEffect>() {

    override val store: Store<FavouriteState, FavouriteWish, FavouriteEffect> = createMeteor(
        configs = MeteorConfigs.build {
            initialState = FavouriteState.Empty
            storeName = "FavouriteViewModel"
            reducer = favouriteReducer
            middlewares = listOf(favouriteMiddleware)
        }
    )
}
