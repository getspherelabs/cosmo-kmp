package io.spherelabs.aboutpresentation

import io.spherelabs.aboutdomain.AboutDomain

sealed interface AboutWish {
    data class OnNameChanged(val name: String) : AboutWish
    data class OnFavouritePlanetChanged(val favouritePlanet: String) : AboutWish
    data class OnDescriptionChanged(val description: String) : AboutWish
    data class OnInterestedInChanged(val interestedIn: String) : AboutWish
    data class OnImageChanged(val image: String) : AboutWish
    data class GetAboutById(val id: String) : AboutWish
    data class GetAboutMe(val about: AboutDomain) : AboutWish
    data class GetAboutFailed(val message: String) : AboutWish
    data class UpdateAbout(val about: AboutDomain) : AboutWish
    data class InsertAbout(val about: AboutDomain) : AboutWish
    data class InsertFailed(val message: String) : AboutWish
    data class Inserted(val message: String) : AboutWish
    data class Updated(val message: String) : AboutWish
    data class UpdateFailed(val message: String) : AboutWish
    object ToggleAboutMode : AboutWish
}
