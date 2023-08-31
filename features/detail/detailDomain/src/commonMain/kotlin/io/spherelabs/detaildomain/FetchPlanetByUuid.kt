package io.spherelabs.detaildomain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface FetchPlanetByUuid {
    fun execute(uuid: String): Flow<Result<PlanetDetailDomain>>
}

class DefaultFetchPlanetByUuid(
    private val repository: DetailRepository
) : FetchPlanetByUuid {

    override fun execute(uuid: String): Flow<Result<PlanetDetailDomain>> {
        return flow {
            try {
                val result = repository.fetchPlanetById(uuid)
                emit(Result.success(result))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }
}
