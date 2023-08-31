package io.spherelabs.starspresentation

import io.spherelabs.starsdomain.StarDomain

data class StarsState(
    val stars: List<StarDomain> = emptyList(),
    val isLoading: Boolean = false
) {
    companion object {
        val Empty = StarsState()
    }
}
