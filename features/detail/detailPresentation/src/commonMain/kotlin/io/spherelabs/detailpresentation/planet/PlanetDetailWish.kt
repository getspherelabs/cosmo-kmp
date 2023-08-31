package io.spherelabs.detailpresentation.planet

import io.spherelabs.detaildomain.FavouriteDetailDomain
import io.spherelabs.detaildomain.PlanetDetailDomain

sealed interface PlanetDetailWish {
    object GetPlanetByUuidLoading : PlanetDetailWish
    data class GetPlanetByUuid(val uuid: String) : PlanetDetailWish
    data class GetPlanetByUuidFailed(val message: String) : PlanetDetailWish
    data class GetPlanet(val planet: PlanetDetailDomain) : PlanetDetailWish
    data class InsertFavourite(val favourite: FavouriteDetailDomain) : PlanetDetailWish
    data class OnFavouriteChanged(val isFavourite: Boolean) : PlanetDetailWish
    data class InsertFailed(val message: String) : PlanetDetailWish
    data class DeleteFavourite(val id: String) : PlanetDetailWish
    data class DeleteFailed(val message: String) : PlanetDetailWish
}
