package io.spherelabs.discoverdomain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface FetchArticles {
    fun execute(): Flow<Result<ArticlesDomain>>
}

class DefaultFetchArticles(
    private val repository: DiscoverRepository
) : FetchArticles {

    override fun execute(): Flow<Result<ArticlesDomain>> {
        return flow {
            try {
                emit(Result.success(repository.fetchArticles()))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }
}
