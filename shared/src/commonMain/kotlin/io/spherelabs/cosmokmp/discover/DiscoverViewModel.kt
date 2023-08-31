package io.spherelabs.cosmokmp.discover

import io.spherelabs.discoverpresentation.ArticleMiddleware
import io.spherelabs.discoverpresentation.DiscoverEffect
import io.spherelabs.discoverpresentation.DiscoverReducer
import io.spherelabs.discoverpresentation.DiscoverState
import io.spherelabs.discoverpresentation.DiscoverWish
import io.spherelabs.discoverpresentation.PopularPlanetsMiddleware
import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor

class DiscoverViewModel(
    private val discoverReducer: DiscoverReducer,
    private val popularPlanetsMiddleware: PopularPlanetsMiddleware,
    private val articleMiddleware: ArticleMiddleware
) : CommonViewModel<DiscoverState, DiscoverWish, DiscoverEffect>() {

    override val store: Store<DiscoverState, DiscoverWish, DiscoverEffect> = createMeteor(
        configs = MeteorConfigs.build {
            initialState = DiscoverState.Empty
            storeName = "DiscoverViewModel"
            reducer = discoverReducer
            middlewares = listOf(popularPlanetsMiddleware, articleMiddleware)
        }
    )
}
