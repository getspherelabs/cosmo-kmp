package io.spherelabs.aboutdomain

interface UpdateAbout {
    suspend fun execute(about: AboutDomain)
}

class DefaultUpdateAbout(
    private val repository: AboutRepository
) : UpdateAbout {

    override suspend fun execute(about: AboutDomain) {
        repository.update(about)
    }
}
