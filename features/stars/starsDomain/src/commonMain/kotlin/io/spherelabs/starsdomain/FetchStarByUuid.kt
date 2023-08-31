package io.spherelabs.starsdomain

import io.spherelabs.starsdomain.repository.StarsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

interface FetchStarByUuid {
    suspend fun execute(uuid: String): Result<StarDomain>
}

class DefaultFetchStarByUuid(
    private val repository: StarsRepository
) : FetchStarByUuid {
    override suspend fun execute(uuid: String): Result<StarDomain> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(
                    repository.fetchStarByUuid(
                        uuid
                    )
                )
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
