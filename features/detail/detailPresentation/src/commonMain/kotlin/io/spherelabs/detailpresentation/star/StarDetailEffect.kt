package io.spherelabs.detailpresentation.star

sealed interface StarDetailEffect {
    data class Failure(val message: String) : StarDetailEffect
}
