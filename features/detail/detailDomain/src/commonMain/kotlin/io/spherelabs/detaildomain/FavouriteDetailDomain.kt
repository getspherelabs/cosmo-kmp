package io.spherelabs.detaildomain

data class FavouriteDetailDomain(
    val id: String,
    val name: String,
    val description: String,
    val size: String,
    val distanceFromSun: String,
    val isFavourite: Boolean,
    val createdTimestamp: String
)
