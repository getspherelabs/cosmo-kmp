package io.spherelabs.searchpresentation

sealed interface SearchEffect {
    data class Failure(val message: String) : SearchEffect
}
