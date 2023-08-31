package io.spherelabs.favouritedomain

interface DeleteFavourite {
    suspend fun execute(id: String)
}

class DefaultDeleteFavourite(
    private val repository: FavouriteRepository
) : DeleteFavourite {
    override suspend fun execute(id: String) {
        repository.deleteFavourite(id)
    }
}
