package io.spherelabs.detaildomain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface FetchArticleByUuid {
    fun execute(uuid: String): Flow<Result<ArticleDetailDomain>>
}

class DefaultFetchArticleByUuid(
    private val repository: DetailRepository
) : FetchArticleByUuid {

    override fun execute(uuid: String): Flow<Result<ArticleDetailDomain>> {
        return flow {
            try {
                val result = repository.fetchArticleByUuid(uuid)
                emit(Result.success(result))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }
}
