package io.spherelabs.network.dto

import io.spherelabs.detaildomain.PlanetDetailDomain
import io.spherelabs.discoverdomain.PlanetDomain
import io.spherelabs.discoverdomain.PlanetsDomain
import io.spherelabs.searchdomain.SearchPlanetDomain
import io.spherelabs.searchdomain.SearchPlanetsDomain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("planet")
data class PlanetDto(
    val id: String,
    val name: String,
    val description: String,
    val size: String,
    val distanceFromSun: String,
    val isPopular: Boolean,
    val createdTimestamp: Long,
    val updatedTimestamp: Long
)

fun PlanetDto.asDomain(): PlanetDomain {
    return PlanetDomain(
        id = id,
        name = name,
        description = description,
        size = size,
        distanceFromSun = distanceFromSun,
        isPopular = isPopular,
        createdTimestamp = createdTimestamp,
        updatedTimestamp = updatedTimestamp
    )
}

fun PlanetDto.asSearchDomain(): SearchPlanetDomain {
    return SearchPlanetDomain(
        id = id,
        name = name,
        description = description,
        size = size,
        distanceFromSun = distanceFromSun,
        isPopular = isPopular,
        createdTimestamp = createdTimestamp,
        updatedTimestamp = updatedTimestamp
    )
}

fun PlanetDto.asDetailDomain(): PlanetDetailDomain {
    return PlanetDetailDomain(
        id = id,
        name = name,
        description = description,
        size = size,
        distanceFromSun = distanceFromSun,
        isPopular = isPopular,
        createdTimestamp = createdTimestamp,
        updatedTimestamp = updatedTimestamp
    )
}

@Serializable
data class PlanetsDto(
    val planets: List<PlanetDto>
)

fun PlanetsDto.asDomain(): PlanetsDomain {
    return PlanetsDomain(planets = this.planets.map { it.asDomain() })
}

fun PlanetsDto.asSearchDomain(): SearchPlanetsDomain {
    return SearchPlanetsDomain(list = this.planets.map { it.asSearchDomain() })
}
