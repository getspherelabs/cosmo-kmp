package io.spherelabs.aboutdomain

interface InsertAbout {
    suspend fun execute(about: AboutDomain)
}

class DefaultInsertAbout(
    private val repository: AboutRepository
) : InsertAbout {

    override suspend fun execute(about: AboutDomain) {
        repository.insert(about)
    }
}
