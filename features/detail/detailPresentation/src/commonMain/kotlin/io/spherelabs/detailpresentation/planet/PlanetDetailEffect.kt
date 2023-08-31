package io.spherelabs.detailpresentation.planet

sealed interface PlanetDetailEffect {
    data class Failure(val message: String) : PlanetDetailEffect
}
