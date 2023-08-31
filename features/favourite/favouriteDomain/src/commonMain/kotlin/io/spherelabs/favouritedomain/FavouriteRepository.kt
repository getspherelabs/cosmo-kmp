package io.spherelabs.favouritedomain

import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    suspend fun insertFavourite(favourite: FavouriteDomain)
    suspend fun deleteFavourite(id: String)
    fun getFavourites(): Flow<List<FavouriteDomain>>
    fun getFavouriteById(id: String): Flow<FavouriteDomain>
    suspend fun isFavourite(id: String): Boolean
}
