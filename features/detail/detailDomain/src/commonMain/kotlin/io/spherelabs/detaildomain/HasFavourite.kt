package io.spherelabs.detaildomain

interface HasFavourite {
    suspend fun execute(id: String): Boolean
}

class DefaultHasFavourite(
    private val repository: DetailRepository
) : HasFavourite {

    override suspend fun execute(id: String): Boolean {
        return repository.isFavourite(id)
    }
}
