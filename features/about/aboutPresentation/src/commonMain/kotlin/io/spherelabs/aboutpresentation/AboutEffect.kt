package io.spherelabs.aboutpresentation

sealed interface AboutEffect {
    data class Failure(val message: String) : AboutEffect
    data class Inserted(val message: String) : AboutEffect
    data class Updated(val message: String) : AboutEffect
}
