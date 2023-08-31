package io.spherelabs.cosmokmp.search

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor
import io.spherelabs.searchpresentation.SearchEffect
import io.spherelabs.searchpresentation.SearchMiddleware
import io.spherelabs.searchpresentation.SearchReducer
import io.spherelabs.searchpresentation.SearchState
import io.spherelabs.searchpresentation.SearchWish

class SearchViewModel(
    private val searchReducer: SearchReducer,
    private val searchMiddleware: SearchMiddleware
) : CommonViewModel<SearchState, SearchWish, SearchEffect>() {

    override val store: Store<SearchState, SearchWish, SearchEffect> = createMeteor(
        configs = MeteorConfigs.build {
            initialState = SearchState.Empty
            storeName = "SearchViewModel"
            reducer = searchReducer
            middlewares = listOf(searchMiddleware)
        }
    )
}
