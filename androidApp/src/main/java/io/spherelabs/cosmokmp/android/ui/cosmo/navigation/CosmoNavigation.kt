package io.spherelabs.cosmokmp.android.ui.cosmo.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

const val cosmoRoute = "cosmo"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.cosmoScreen(
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    this.composable(cosmoRoute, content = content)
}
