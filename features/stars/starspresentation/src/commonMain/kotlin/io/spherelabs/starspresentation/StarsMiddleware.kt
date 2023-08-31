package io.spherelabs.starspresentation

import io.spherelabs.meteor.middleware.Middleware
import io.spherelabs.starsdomain.FetchStars
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart

class StarsMiddleware(
    private val fetchStars: FetchStars
) : Middleware<StarsState, StarsWish> {

    override suspend fun process(
        state: StarsState,
        wish: StarsWish,
        next: suspend (StarsWish) -> Unit
    ) {
        return when (wish) {
            StarsWish.StarsStarted -> {
                fetchStars.execute()
                    .onStart {
                        next.invoke(StarsWish.StarsLoading)
                    }
                    .collectLatest { result ->
                        result.onSuccess { newStars ->
                            next.invoke(StarsWish.GetStars(newStars.list))
                        }.onFailure { newException ->
                            val failureMessage = newException.message ?: "Error is occurred."
                            next.invoke(StarsWish.StarsFailed(failureMessage))
                        }
                    }
            }

            else -> {}
        }
    }
}
