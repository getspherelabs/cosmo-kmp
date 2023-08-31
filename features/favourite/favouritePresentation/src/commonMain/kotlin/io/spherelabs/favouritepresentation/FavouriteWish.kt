package io.spherelabs.favouritepresentation

import io.spherelabs.favouritedomain.FavouriteDomain

sealed interface FavouriteWish {
    object GetStarted : FavouriteWish
    object GetLoading : FavouriteWish
    data class GetFavourite(val favourites: List<FavouriteDomain>) : FavouriteWish
    data class GetFailed(val message: String) : FavouriteWish
}
