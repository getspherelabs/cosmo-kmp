package io.spherelabs.local.repository

import io.spherelabs.favouritedomain.FavouriteDomain
import io.spherelabs.favouritedomain.FavouriteRepository
import io.spherelabs.local.db.FavouriteDao
import io.spherelabs.local.mapper.asDomain
import io.spherelabs.local.mapper.asEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DefaultFavouriteRepository(
    private val dao: FavouriteDao
) : FavouriteRepository {

    override suspend fun insertFavourite(favourite: FavouriteDomain) {
        withContext(Dispatchers.IO) {
            dao.insertFavourite(favourite.asEntity())
        }
    }

    override suspend fun deleteFavourite(id: String) {
        withContext(Dispatchers.IO) {
            dao.deleteFavourite(id)
        }
    }

    override fun getFavourites(): Flow<List<FavouriteDomain>> {
        return dao.getFavourites().map { it.map { newFavourites -> newFavourites.asDomain() } }
    }

    override fun getFavouriteById(id: String): Flow<FavouriteDomain> {
        return dao.getFavouriteById(id).map { it.asDomain() }
    }

    override suspend fun isFavourite(id: String): Boolean {
        return dao.isFavourite(id)
    }
}
