package io.spherelabs.favouritedomain

import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

interface GetFavourites {
    fun execute(): Flow<Result<List<FavouriteDomain>>>
}

class DefaultGetFavourites(
    private val repository: FavouriteRepository
) : GetFavourites {
    override fun execute(): Flow<Result<List<FavouriteDomain>>> {
        return channelFlow {
            try {
                repository.getFavourites().collectLatest {
                    trySend(Result.success(it))
                }
            } catch (e: Exception) {
                trySend(Result.failure(e))
            }
            awaitClose { cancel() }
        }
    }
}
