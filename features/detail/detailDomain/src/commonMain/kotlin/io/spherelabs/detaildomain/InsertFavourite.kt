package io.spherelabs.detaildomain

interface InsertFavourite {
    suspend fun execute(favourite: FavouriteDetailDomain)
}

class DefaultInsertFavourite(
    private val repository: DetailRepository
) : InsertFavourite {

    override suspend fun execute(favourite: FavouriteDetailDomain) {
        repository.insertFavourite(favourite)
    }
}
