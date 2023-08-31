package io.spherelabs.local.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
import iospherelabslocaldb.About
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

interface AboutDao {
    suspend fun insert(about: About)
    suspend fun update(about: About)
    fun getAboutById(id: String): Flow<About>
}

class DefaultAboutDao(
    db: CosmoDatabase
) : AboutDao {

    private val queries = db.aboutQueries

    override suspend fun insert(about: About) {
        queries.transaction {
            queries.insert(
                id = about.id,
                name = about.name,
                description = about.description,
                favouritePlanet = about.favouritePlanet,
                interestedIn = about.interestedIn,
                image = about.image
            )
        }
    }

    override suspend fun update(about: About) {
        queries.transaction {
            queries.update(
                name = about.name,
                description = about.description,
                favouritePlanet = about.favouritePlanet,
                interestedIn = about.interestedIn,
                image = about.image
            )
        }
    }

    override fun getAboutById(id: String): Flow<About> {
        return queries.getAboutById(id).asFlow().mapToOne(Dispatchers.IO)
    }
}
