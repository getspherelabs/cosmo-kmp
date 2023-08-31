package io.spherelabs.detaildomain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface FetchStarByUuid {
    fun execute(uuid: String): Flow<Result<StarDetailDomain>>
}

class DefaultFetchStarByUuid(
    private val repository: DetailRepository
) : FetchStarByUuid {

    override fun execute(uuid: String): Flow<Result<StarDetailDomain>> {
        return flow {
            try {
                val result = repository.fetchStarByUuid(uuid)
                emit(Result.success(result))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}
