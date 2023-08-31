package io.spherelabs.cosmokmp.android.ui.discover.navigation

import androidx.navigation.NavGraphBuilder
import io.spherelabs.cosmokmp.android.common.animatedComposable
import io.spherelabs.cosmokmp.android.ui.discover.DiscoverRoute

const val discoverRoute = "discover"

fun NavGraphBuilder.discoverScreen() {
    this.animatedComposable(discoverRoute) {
        DiscoverRoute(onNavigateToStars = { /*TODO*/ }, onNavigateToDetail = { })
    }
}
