package io.spherelabs.discoverpresentation

sealed interface DiscoverEffect {
    data class Failure(val message: String) : DiscoverEffect
}
