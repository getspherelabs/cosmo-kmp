package io.spherelabs.searchpresentation

import io.spherelabs.searchdomain.SearchPlanetDomain

sealed interface SearchWish {
    data class OnQueryChanged(val query: String) : SearchWish
    object SearchLoading : SearchWish
    data class GetPlanets(val planets: List<SearchPlanetDomain>) : SearchWish
    data class SearchFailed(val message: String) : SearchWish
}
