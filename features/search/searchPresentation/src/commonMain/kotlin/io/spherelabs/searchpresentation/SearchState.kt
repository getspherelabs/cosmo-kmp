package io.spherelabs.searchpresentation

import io.spherelabs.searchdomain.SearchPlanetDomain

data class SearchState(
    val planets: List<SearchPlanetDomain> = emptyList(),
    val isLoading: Boolean = false
) {
    companion object {
        val Empty = SearchState()
    }
}
