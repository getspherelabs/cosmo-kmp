package io.spherelabs.discoverdomain

data class PlanetDomain(
    val id: String,
    val name: String,
    val description: String,
    val size: String,
    val distanceFromSun: String,
    val isPopular: Boolean,
    val createdTimestamp: Long,
    val updatedTimestamp: Long
)

data class PlanetsDomain(
    val planets: List<PlanetDomain>
)
