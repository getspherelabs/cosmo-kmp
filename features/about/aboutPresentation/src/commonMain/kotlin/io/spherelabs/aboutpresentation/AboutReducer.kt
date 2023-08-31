package io.spherelabs.aboutpresentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class AboutReducer : Reducer<AboutState, AboutWish, AboutEffect> {

    override fun reduce(currentState: AboutState, wish: AboutWish): Change<AboutState, AboutEffect> {
        return when (wish) {
            is AboutWish.GetAboutFailed -> {
                change {
                    effect { AboutEffect.Failure(wish.message) }
                }
            }
            is AboutWish.GetAboutMe -> {
                val newName = wish.about.name
                val newDesc = wish.about.description
                val newFavouritePlanet = wish.about.favouritePlanet
                val newInterestedIn = wish.about.interestedIn
                val newImage = wish.about.image

                change {
                    state {
                        currentState.copy(
                            name = newName,
                            description = newDesc,
                            favouritePlanet = newFavouritePlanet,
                            interestedIn = newInterestedIn,
                            image = newImage
                        )
                    }
                }
            }
            is AboutWish.InsertFailed -> effect { AboutEffect.Failure(wish.message) }
            is AboutWish.Inserted -> {
                effect { AboutEffect.Inserted(wish.message) }
            }
            is AboutWish.OnDescriptionChanged -> {
                change {
                    state { currentState.copy(description = wish.description) }
                }
            }
            is AboutWish.OnFavouritePlanetChanged -> {
                change {
                    state {
                        currentState.copy(favouritePlanet = wish.favouritePlanet)
                    }
                }
            }
            is AboutWish.OnImageChanged -> {
                expect { currentState.copy(image = wish.image) }
            }
            is AboutWish.OnInterestedInChanged -> {
                expect { currentState.copy(interestedIn = wish.interestedIn) }
            }
            is AboutWish.OnNameChanged -> {
                expect { currentState.copy(name = wish.name) }
            }
            is AboutWish.UpdateFailed -> effect { AboutEffect.Failure(wish.message) }
            is AboutWish.Updated -> {
                effect { AboutEffect.Updated(wish.message) }
            }
            AboutWish.ToggleAboutMode -> {
                val mode = currentState.mode

                val newAboutMode = if (
                    mode == AboutMode.View
                ) {
                    AboutMode.Edit
                } else {
                    AboutMode.View
                }

                expect { currentState.copy(mode = newAboutMode) }
            }
            else -> unexpected { currentState }
        }
    }
}
