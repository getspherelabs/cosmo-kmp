package io.spherelabs.searchpresentation

import io.spherelabs.meteor.middleware.Middleware
import io.spherelabs.searchdomain.SearchPlanetsByName
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart

class SearchMiddleware(
    private val searchPlanetsByName: SearchPlanetsByName
) : Middleware<SearchState, SearchWish> {

    override suspend fun process(
        state: SearchState,
        wish: SearchWish,
        next: suspend (SearchWish) -> Unit
    ) {
        when (wish) {
            is SearchWish.OnQueryChanged -> {
                searchPlanetsByName.execute(wish.query)
                    .onStart { next.invoke(SearchWish.SearchLoading) }
                    .collectLatest { result ->
                        result.onSuccess { newPlanets ->
                            next.invoke(SearchWish.GetPlanets(newPlanets.list))
                        }.onFailure { newException ->
                            val failureMessage = newException.message ?: "Error occurred."
                            next.invoke(SearchWish.SearchFailed(failureMessage))
                        }
                    }
            }

            else -> {}
        }
    }
}
