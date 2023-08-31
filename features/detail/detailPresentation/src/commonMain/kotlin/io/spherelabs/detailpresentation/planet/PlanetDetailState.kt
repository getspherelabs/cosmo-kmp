package io.spherelabs.detailpresentation.planet

import io.spherelabs.detaildomain.PlanetDetailDomain

data class PlanetDetailState(
    val planet: PlanetDetailDomain? = null,
    val isLoading: Boolean = false,
    val isFavourite: Boolean = false
) {
    companion object {
        val Empty = PlanetDetailState()
    }
}
