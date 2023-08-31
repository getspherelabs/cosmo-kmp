package io.spherelabs.detailpresentation.star

import io.spherelabs.detaildomain.FavouriteDetailDomain
import io.spherelabs.detaildomain.StarDetailDomain

sealed interface StarDetailWish {
    object GetStarByUuidLoading : StarDetailWish
    data class GetStarByUuid(val uuid: String) : StarDetailWish
    data class GetStarByUuidFailed(val message: String) : StarDetailWish
    data class GetStar(val star: StarDetailDomain) : StarDetailWish
    data class InsertFavourite(val favourite: FavouriteDetailDomain) : StarDetailWish
    data class OnFavouriteChanged(val isFavourite: Boolean) : StarDetailWish
    data class InsertFailed(val message: String) : StarDetailWish
    data class DeleteFavourite(val id: String) : StarDetailWish
    data class DeleteFailed(val message: String) : StarDetailWish
}
