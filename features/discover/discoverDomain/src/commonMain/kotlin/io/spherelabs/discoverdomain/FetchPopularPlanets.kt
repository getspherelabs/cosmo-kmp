package io.spherelabs.discoverdomain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface FetchPopularPlanets {
    fun execute(): Flow<Result<PlanetsDomain>>
}

class DefaultFetchPopularPlanets(
    private val repository: DiscoverRepository
) : FetchPopularPlanets {

    override fun execute(): Flow<Result<PlanetsDomain>> {
        return flow {
            try {
                emit(Result.success(repository.fetchPopularPlanets()))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }
}
