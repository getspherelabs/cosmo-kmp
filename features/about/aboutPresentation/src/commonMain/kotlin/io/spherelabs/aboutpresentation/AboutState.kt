package io.spherelabs.aboutpresentation

data class AboutState(
    val name: String = "",
    val description: String = "",
    val favouritePlanet: String = "",
    val interestedIn: String = "",
    val image: String = "",
    val mode: AboutMode = AboutMode.View
) {
    companion object {
        val Empty = AboutState()
    }
}
