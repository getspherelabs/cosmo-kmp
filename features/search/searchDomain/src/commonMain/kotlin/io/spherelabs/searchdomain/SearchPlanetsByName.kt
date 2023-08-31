package io.spherelabs.searchdomain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SearchPlanetsByName {
    fun execute(query: String): Flow<Result<SearchPlanetsDomain>>
}

class DefaultSearchPlanetsByName(
    private val repository: SearchRepository
) : SearchPlanetsByName {

    override fun execute(query: String): Flow<Result<SearchPlanetsDomain>> {
        return flow {
            try {
                emit(Result.success(repository.searchPlanetsByName(query)))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }
}
