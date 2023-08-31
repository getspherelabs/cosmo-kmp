package io.spherelabs.local.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import iospherelabslocaldb.Favourite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

interface FavouriteDao {
    suspend fun insertFavourite(favourite: Favourite)
    suspend fun deleteFavourite(id: String)
    fun getFavourites(): Flow<List<Favourite>>
    fun getFavouriteById(id: String): Flow<Favourite>
    suspend fun isFavourite(id: String): Boolean
}

class DefaultFavouriteDao(
    db: CosmoDatabase
) : FavouriteDao {
    private val queries = db.favouriteQueries

    override suspend fun insertFavourite(favourite: Favourite) {
        return queries.insertFavourite(
            favourite.id,
            favourite.name,
            favourite.description,
            favourite.size,
            favourite.distanceFromSun,
            favourite.isFavourite,
            favourite.createdTimestamp
        )
    }

    override suspend fun deleteFavourite(id: String) {
        queries.transaction {
            queries.deleteFavourite(id)
        }
    }

    override fun getFavourites(): Flow<List<Favourite>> {
        return queries.getFavourites().asFlow().mapToList(Dispatchers.IO)
    }

    override fun getFavouriteById(id: String): Flow<Favourite> {
        return queries.getFavouriteById(id).asFlow().mapToOne(Dispatchers.IO)
    }

    override suspend fun isFavourite(id: String): Boolean {
        val isFavourite = queries.isFavourite(id).executeAsOne().isFavourite
        return isFavourite?.let {
            it == 1L
        } ?: false
    }
}
