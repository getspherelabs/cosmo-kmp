package io.spherelabs.cosmokmp.about

import io.spherelabs.aboutpresentation.AboutEffect
import io.spherelabs.aboutpresentation.AboutMiddleware
import io.spherelabs.aboutpresentation.AboutReducer
import io.spherelabs.aboutpresentation.AboutState
import io.spherelabs.aboutpresentation.AboutWish
import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor

class AboutViewModel(
    private val aboutReducer: AboutReducer,
    private val aboutMiddleware: AboutMiddleware
) : CommonViewModel<AboutState, AboutWish, AboutEffect>() {

    override val store: Store<AboutState, AboutWish, AboutEffect> = createMeteor(
        configs = MeteorConfigs.build {
            initialState = AboutState.Empty
            storeName = ABOUT_STORE_NAME
            reducer = aboutReducer
            middlewares = listOf(aboutMiddleware)
        }
    )

    companion object {
        private const val ABOUT_STORE_NAME = "AboutViewModel"
    }
}
