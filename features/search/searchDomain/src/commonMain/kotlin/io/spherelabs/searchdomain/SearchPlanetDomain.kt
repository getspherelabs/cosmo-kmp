package io.spherelabs.searchdomain

data class SearchPlanetDomain(
    val id: String,
    val name: String,
    val description: String,
    val size: String,
    val distanceFromSun: String,
    val isPopular: Boolean,
    val createdTimestamp: Long,
    val updatedTimestamp: Long
)

data class SearchPlanetsDomain(
    val list: List<SearchPlanetDomain>
)
