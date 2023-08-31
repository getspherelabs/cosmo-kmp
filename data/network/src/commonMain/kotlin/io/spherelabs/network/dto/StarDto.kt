package io.spherelabs.network.dto

import io.spherelabs.detaildomain.StarDetailDomain
import io.spherelabs.starsdomain.StarDomain
import io.spherelabs.starsdomain.StarsDomain

@kotlinx.serialization.Serializable
data class StarDto(
    val id: String,
    val name: String,
    val description: String,
    val size: String,
    val distanceFromSun: String,
    val isPopular: Boolean,
    val createdTimestamp: Long,
    val updatedTimestamp: Long
)

@kotlinx.serialization.Serializable
data class StarsDto(
    val list: List<StarDto>
)

fun StarDto.asDomain(): StarDomain {
    return StarDomain(
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

fun StarDto.asDetailDomain(): StarDetailDomain {
    return StarDetailDomain(
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

fun StarsDto.asDomain(): StarsDomain {
    return StarsDomain(
        list = this.list.map { it.asDomain() }
    )
}
