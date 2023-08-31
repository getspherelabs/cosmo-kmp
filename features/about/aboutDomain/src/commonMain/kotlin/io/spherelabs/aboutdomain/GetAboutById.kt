package io.spherelabs.aboutdomain

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

interface GetAboutById {
    fun execute(id: String): Flow<Result<AboutDomain>>
}

class DefaultGetAboutById(
    private val repository: AboutRepository
) : GetAboutById {

    override fun execute(id: String): Flow<Result<AboutDomain>> {
        return channelFlow {
            repository.getAboutById(id).collectLatest {
                try {
                    trySend(Result.success(it))
                } catch (e: Exception) {
                    trySend(Result.failure(e))
                }
            }
            awaitClose { close() }
        }
    }
}
