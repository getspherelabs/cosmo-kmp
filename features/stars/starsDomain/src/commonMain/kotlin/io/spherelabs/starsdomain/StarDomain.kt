package io.spherelabs.starsdomain

data class StarDomain(
    val id: String,
    val name: String,
    val description: String,
    val size: String,
    val distanceFromSun: String,
    val isPopular: Boolean,
    val createdTimestamp: Long,
    val updatedTimestamp: Long
)

data class StarsDomain(
    val list: List<StarDomain>
)
