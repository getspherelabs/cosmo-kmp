package io.spherelabs.cosmokmp.android.ui.cosmo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import io.spherelabs.cosmokmp.android.common.animatedComposable
import io.spherelabs.cosmokmp.android.ui.cosmo.MainScreen

const val mainRoute = "main"

fun NavController.navigateToMain() {
    this.navigate(mainRoute)
}

fun NavGraphBuilder.mainScreen() {
    this.animatedComposable(mainRoute) {
        MainScreen()
    }
}
