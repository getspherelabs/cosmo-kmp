package io.spherelabs.favouritedomain

interface InsertFavourite {
    suspend fun execute(favourite: FavouriteDomain)
}

class DefaultInsertFavourite(
    private val repository: FavouriteRepository
) : InsertFavourite {
    override suspend fun execute(favourite: FavouriteDomain) {
        repository.insertFavourite(favourite)
    }
}
