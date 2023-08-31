package io.spherelabs.favouritepresentation

sealed interface FavouriteEffect {
    data class Failure(val message: String) : FavouriteEffect
}
