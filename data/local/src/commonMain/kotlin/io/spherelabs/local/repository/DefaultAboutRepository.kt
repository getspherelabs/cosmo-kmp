package io.spherelabs.local.repository

import io.spherelabs.aboutdomain.AboutDomain
import io.spherelabs.aboutdomain.AboutRepository
import io.spherelabs.local.db.AboutDao
import io.spherelabs.local.mapper.asDomain
import io.spherelabs.local.mapper.asEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DefaultAboutRepository(
    private val dao: AboutDao
) : AboutRepository {

    override suspend fun insert(about: AboutDomain) {
        withContext(Dispatchers.IO) {
            dao.insert(about.asEntity())
        }
    }

    override suspend fun update(about: AboutDomain) {
        withContext(Dispatchers.IO) {
            dao.update(about.asEntity())
        }
    }

    override fun getAboutById(id: String): Flow<AboutDomain> {
        return dao.getAboutById(id).map { it.asDomain() }
    }
}
