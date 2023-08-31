package io.spherelabs.starsdomain

import io.spherelabs.starsdomain.repository.StarsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface FetchStars {
    fun execute(): Flow<Result<StarsDomain>>
}

class DefaultFetchStars(
    private val repository: StarsRepository
) : FetchStars {
    override fun execute(): Flow<Result<StarsDomain>> {
        return flow {
            try {
                emit(Result.success(repository.fetchStars()))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}
