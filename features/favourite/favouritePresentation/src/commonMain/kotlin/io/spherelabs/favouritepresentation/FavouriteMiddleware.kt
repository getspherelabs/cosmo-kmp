package io.spherelabs.favouritepresentation

import io.spherelabs.favouritedomain.GetFavourites
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart

class FavouriteMiddleware(
    private val getFavourites: GetFavourites
) : Middleware<FavouriteState, FavouriteWish> {

    override suspend fun process(
        state: FavouriteState,
        wish: FavouriteWish,
        next: suspend (FavouriteWish) -> Unit
    ) {
        when (wish) {
            FavouriteWish.GetStarted -> {
                getFavourites.execute()
                    .onStart {
                        next.invoke(FavouriteWish.GetLoading)
                    }.collectLatest { result ->
                        result.onSuccess { newFavourites ->
                            next.invoke(FavouriteWish.GetFavourite(newFavourites))
                        }.onFailure { newException ->
                            val failureMessage = newException.message ?: "Error is occurred."
                            next.invoke(FavouriteWish.GetFailed(failureMessage))
                        }
                    }
            }

            else -> {}
        }
    }
}
