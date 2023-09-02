package io.spherelabs.starspresentation

import io.spherelabs.starsdomain.StarDomain

sealed interface StarsWish {
    object StarsLoading : StarsWish
    object StarsStarted : StarsWish
    data class StarsFailed(val message: String) : StarsWish
    data class GetStars(val list: List<StarDomain>) : StarsWish
}
