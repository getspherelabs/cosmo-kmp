package io.spherelabs.detailpresentation.star

import io.spherelabs.detaildomain.StarDetailDomain

data class StarDetailState(
    val star: StarDetailDomain? = null,
    val isLoading: Boolean = false,
    val isFavourite: Boolean = false
) {
    companion object {
        val Empty = StarDetailState()
    }
}
