package io.spherelabs.detaildomain

interface DeleteFavourite {
    suspend fun execute(id: String)
}

class DefaultDeleteFavourite(
    private val repository: DetailRepository
) : DeleteFavourite {

    override suspend fun execute(id: String) {
        repository.deleteFavouriteByUuid(id)
    }
}
