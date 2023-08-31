package io.spherelabs.detailpresentation.planet

import io.spherelabs.detaildomain.DeleteFavourite
import io.spherelabs.detaildomain.FetchPlanetByUuid
import io.spherelabs.detaildomain.InsertFavourite
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart

class PlanetMiddleware(
    private val fetchPlanetByUuid: FetchPlanetByUuid,
    private val insertFavourite: InsertFavourite,
    private val deleteFavourite: DeleteFavourite
) : Middleware<PlanetDetailState, PlanetDetailWish> {

    override suspend fun process(
        state: PlanetDetailState,
        wish: PlanetDetailWish,
        next: suspend (PlanetDetailWish) -> Unit
    ) {
        when (wish) {
            is PlanetDetailWish.GetPlanetByUuid -> {
                fetchPlanetByUuid.execute(wish.uuid)
                    .onStart {
                        next.invoke(PlanetDetailWish.GetPlanetByUuidLoading)
                    }
                    .collectLatest { result ->
                        result.onSuccess { newPlanet ->
                            next.invoke(PlanetDetailWish.GetPlanet(newPlanet))
                        }.onFailure {
                            val failureMessage = it.message ?: "Error is occurred."
                            next.invoke(PlanetDetailWish.GetPlanetByUuidFailed(failureMessage))
                        }
                    }
            }
            is PlanetDetailWish.InsertFavourite -> {
                runCatching {
                    insertFavourite.execute(wish.favourite)
                }.onSuccess {
                    next.invoke(PlanetDetailWish.OnFavouriteChanged(true))
                }.onFailure {
                    val failureMessage = it.message ?: "Error is occurred."
                    next.invoke(PlanetDetailWish.InsertFailed(failureMessage))
                }
            }
            is PlanetDetailWish.DeleteFavourite -> {
                runCatching {
                    deleteFavourite.execute(wish.id)
                }.onSuccess {
                    next.invoke(PlanetDetailWish.OnFavouriteChanged(false))
                }.onFailure {
                    val failureMessage = it.message ?: "Error is occurred."
                    next.invoke(PlanetDetailWish.DeleteFailed(failureMessage))
                }
            }
            else -> {}
        }
    }
}
