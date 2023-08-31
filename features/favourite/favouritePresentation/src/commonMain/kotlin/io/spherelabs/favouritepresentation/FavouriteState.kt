package io.spherelabs.favouritepresentation

import io.spherelabs.favouritedomain.FavouriteDomain

data class FavouriteState(
    val favourites: List<FavouriteDomain> = emptyList(),
    val isLoading: Boolean = false
) {
    companion object {
        val Empty = FavouriteState()
    }
}
