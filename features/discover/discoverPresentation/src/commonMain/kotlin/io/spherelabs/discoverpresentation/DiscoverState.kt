package io.spherelabs.discoverpresentation

import io.spherelabs.discoverdomain.ArticleDomain
import io.spherelabs.discoverdomain.PlanetDomain

data class DiscoverState(
    val article: ArticleDomain? = null,
    val popularPlanets: List<PlanetDomain> = emptyList(),
    val isLoading: Boolean = false,
    var isArticleLoading: Boolean = false
) {
    companion object {
        val Empty = DiscoverState()
    }
}
