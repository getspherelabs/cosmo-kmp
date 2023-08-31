package io.spherelabs.local.mapper

import io.spherelabs.aboutdomain.AboutDomain
import iospherelabslocaldb.About

fun About.asDomain(): AboutDomain {
    return AboutDomain(
        id = this.id,
        name = this.name ?: "",
        description = this.description ?: "",
        favouritePlanet = this.favouritePlanet ?: "",
        interestedIn = this.interestedIn ?: "",
        image = this.image ?: ""
    )
}

fun AboutDomain.asEntity(): About {
    return About(
        id = this.id,
        name = this.name,
        description = this.description,
        favouritePlanet = this.favouritePlanet,
        interestedIn = this.interestedIn,
        image = this.image
    )
}
