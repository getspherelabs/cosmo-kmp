package io.spherelabs.cosmokmp.detail

import io.spherelabs.detailpresentation.star.StarDetailEffect
import io.spherelabs.detailpresentation.star.StarDetailState
import io.spherelabs.detailpresentation.star.StarDetailWish
import io.spherelabs.detailpresentation.star.StarMiddleware
import io.spherelabs.detailpresentation.star.StarReducer
import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor

class StarDetailViewModel(
    private val starDetailReducer: StarReducer,
    private val starDetailMiddleware: StarMiddleware
) : CommonViewModel<StarDetailState, StarDetailWish, StarDetailEffect>() {

    override val store: Store<StarDetailState, StarDetailWish, StarDetailEffect> = createMeteor(
        configs = MeteorConfigs.build {
            initialState = StarDetailState.Empty
            storeName = STAR_STORE_NAME
            reducer = starDetailReducer
            middlewares = listOf(starDetailMiddleware)
        }
    )

    companion object {
        private const val STAR_STORE_NAME = "StarDetailViewModel"
    }
}
