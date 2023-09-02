package io.spherelabs.starspresentation

sealed interface StarsEffect {
    data class Failure(val message: String) : StarsEffect
}
