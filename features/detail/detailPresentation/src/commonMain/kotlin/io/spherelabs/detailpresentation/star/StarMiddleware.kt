package io.spherelabs.detailpresentation.star

import io.spherelabs.detaildomain.DeleteFavourite
import io.spherelabs.detaildomain.FetchStarByUuid
import io.spherelabs.detaildomain.InsertFavourite
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart

class StarMiddleware(
    private val fetchStarByUuid: FetchStarByUuid,
    private val insertFavourite: InsertFavourite,
    private val deleteFavourite: DeleteFavourite
) : Middleware<StarDetailState, StarDetailWish> {

    override suspend fun process(
        state: StarDetailState,
        wish: StarDetailWish,
        next: suspend (StarDetailWish) -> Unit
    ) {
        when (wish) {
            is StarDetailWish.GetStarByUuid -> {
                fetchStarByUuid.execute(wish.uuid)
                    .onStart {
                        next.invoke(StarDetailWish.GetStarByUuidLoading)
                    }
                    .collectLatest { result ->
                        result.onSuccess { newStar ->
                            next.invoke(StarDetailWish.GetStar(newStar))
                        }.onFailure {
                            val failureMessage = it.message ?: "Error is occurred."
                            next.invoke(StarDetailWish.GetStarByUuidFailed(failureMessage))
                        }
                    }
            }
            is StarDetailWish.InsertFavourite -> {
                runCatching {
                    insertFavourite.execute(wish.favourite)
                }.onSuccess {
                    next.invoke(StarDetailWish.OnFavouriteChanged(true))
                }.onFailure {
                    val failureMessage = it.message ?: "Error is occurred."
                    next.invoke(StarDetailWish.InsertFailed(failureMessage))
                }
            }
            is StarDetailWish.DeleteFavourite -> {
                runCatching {
                    deleteFavourite.execute(wish.id)
                }.onSuccess {
                    next.invoke(StarDetailWish.OnFavouriteChanged(false))
                }.onFailure {
                    val failureMessage = it.message ?: "Error is occurred."
                    next.invoke(StarDetailWish.DeleteFailed(failureMessage))
                }
            }
            else -> {}
        }
    }
}
