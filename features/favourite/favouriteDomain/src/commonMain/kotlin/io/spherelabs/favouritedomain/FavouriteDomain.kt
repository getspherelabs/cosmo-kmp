package io.spherelabs.favouritedomain

data class FavouriteDomain(
    val id: String,
    val name: String,
    val description: String,
    val size: String,
    val distanceFromSun: String,
    val isFavourite: Boolean,
    val createdTimestamp: String
)
