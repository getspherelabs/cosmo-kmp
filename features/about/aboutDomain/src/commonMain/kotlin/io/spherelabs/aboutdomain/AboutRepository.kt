package io.spherelabs.aboutdomain

import kotlinx.coroutines.flow.Flow

interface AboutRepository {
    suspend fun insert(about: AboutDomain)
    suspend fun update(about: AboutDomain)
    fun getAboutById(id: String): Flow<AboutDomain>
}
