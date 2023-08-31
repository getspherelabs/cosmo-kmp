package io.spherelabs.discoverpresentation

import io.spherelabs.discoverdomain.FetchPopularPlanets
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart

class PopularPlanetsMiddleware(
    private val fetchPopularPlanets: FetchPopularPlanets
) : Middleware<DiscoverState, DiscoverWish> {

    override suspend fun process(
        state: DiscoverState,
        wish: DiscoverWish,
        next: suspend (DiscoverWish) -> Unit
    ) {
        when (wish) {
            DiscoverWish.PopularPlanetsStarted -> {
                fetchPopularPlanets.execute()
                    .onStart {
                        next.invoke(DiscoverWish.PopularPlanetsLoading)
                    }
                    .collectLatest { result ->
                        result.onSuccess { newPlanets ->
                            next.invoke(DiscoverWish.GetPopularPlanets(newPlanets.planets))
                        }.onFailure { newException ->
                            val failureMessage = newException.message ?: "Error is occurred."
                            next.invoke(DiscoverWish.PopularPlanetsFailed(failureMessage))
                        }
                    }
            }
            else -> {}
        }
    }
}
