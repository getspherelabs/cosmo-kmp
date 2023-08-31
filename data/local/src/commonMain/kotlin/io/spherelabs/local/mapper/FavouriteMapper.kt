package io.spherelabs.local.mapper

import io.spherelabs.detaildomain.FavouriteDetailDomain
import io.spherelabs.favouritedomain.FavouriteDomain
import iospherelabslocaldb.Favourite

fun Favourite.asDomain(): FavouriteDomain {
    return FavouriteDomain(
        id = id,
        name = name,
        description = description,
        size = size,
        distanceFromSun = distanceFromSun,
        isFavourite = isFavourite?.toBoolean() ?: false,
        createdTimestamp = this.createdTimestamp
    )
}

fun FavouriteDomain.asEntity(): Favourite {
    return Favourite(
        id,
        name,
        description,
        size,
        distanceFromSun,
        isFavourite.toLong(),
        createdTimestamp
    )
}

fun FavouriteDetailDomain.asEntity(): Favourite {
    return Favourite(
        id,
        name,
        description,
        size,
        distanceFromSun,
        isFavourite.toLong(),
        createdTimestamp
    )
}

fun Long.toBoolean(): Boolean {
    return this == 1L
}

fun Boolean.toLong(): Long {
    return if (this) 1L else 0L
}
