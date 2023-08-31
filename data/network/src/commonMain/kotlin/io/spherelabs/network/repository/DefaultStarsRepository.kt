package io.spherelabs.network.repository

import io.spherelabs.network.dto.asDomain
import io.spherelabs.network.service.StarService
import io.spherelabs.starsdomain.StarDomain
import io.spherelabs.starsdomain.StarsDomain
import io.spherelabs.starsdomain.repository.StarsRepository

class DefaultStarsRepository(
    private val starService: StarService
) : StarsRepository {

    override suspend fun fetchStars(): StarsDomain {
        return starService.fetchStars().asDomain()
    }

    override suspend fun fetchStarByUuid(uuid: String): StarDomain {
        return starService.fetchStarByUuid(uuid).asDomain()
    }
}
