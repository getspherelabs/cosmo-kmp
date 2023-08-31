package io.spherelabs.starsdomain.repository

import io.spherelabs.starsdomain.StarDomain
import io.spherelabs.starsdomain.StarsDomain

interface StarsRepository {
    suspend fun fetchStars(): StarsDomain
    suspend fun fetchStarByUuid(uuid: String): StarDomain
}
