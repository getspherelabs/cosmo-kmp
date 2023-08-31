package io.spherelabs.cosmokmp.android.ui.cosmo

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import io.spherelabs.cosmokmp.android.R
import io.spherelabs.cosmokmp.android.common.animatedComposable
import io.spherelabs.cosmokmp.android.ui.about.AboutRoute
import io.spherelabs.cosmokmp.android.ui.detail.planet.PlanetDetailRoute
import io.spherelabs.cosmokmp.android.ui.detail.star.StarDetailRoute
import io.spherelabs.cosmokmp.android.ui.discover.BottomNavComponent
import io.spherelabs.cosmokmp.android.ui.discover.DiscoverRoute
import io.spherelabs.cosmokmp.android.ui.favourite.FavouriteRoute
import io.spherelabs.cosmokmp.android.ui.search.SearchRoute
import io.spherelabs.cosmokmp.android.ui.stars.StarsRoute
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navHostController = rememberAnimatedNavController()

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        backgroundColor = colorResource(id = R.color.dark_gray),
        bottomBar = {
            BottomNavComponent(
                navController = navHostController,
                backgroundColor = colorResource(id = R.color.nero).copy(alpha = 0.5f),
                contentColor = colorResource(
                    id = R.color.white
                )
            )
        }
    ) { newPaddingValues ->
        Box(modifier = modifier.padding(newPaddingValues)) {
            MainNavigation(navController = navHostController)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavigation(navController: NavHostController = rememberAnimatedNavController()) {
    AnimatedNavHost(navController = navController, startDestination = "discover") {
        composable("discover") {
            DiscoverRoute(
                viewModel = koinViewModel(),
                onNavigateToStars = {
                    navController.navigate("stars")
                },
                onNavigateToDetail = { newId ->
                    navController.navigate("planet_detail/$newId")
                }
            )
        }
        composable("search") {
            SearchRoute(viewModel = koinViewModel())
        }
        composable("favourite") {
            FavouriteRoute(viewModel = koinViewModel())
        }
        composable("about") {
            AboutRoute(viewModel = koinViewModel())
        }
        animatedComposable("stars") {
            StarsRoute(
                viewModel = koinViewModel(),
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateDetail = { newId ->
                    navController.navigate("star_detail/$newId")
                }
            )
        }
        animatedComposable("planet_detail/{id}") { entry ->
            val newId = entry.arguments?.getString("id") ?: "0"
            PlanetDetailRoute(viewModel = koinViewModel(), id = newId)
        }
        animatedComposable("star_detail/{id}") { entry ->
            val newId = entry.arguments?.getString("id") ?: "0"
            StarDetailRoute(viewModel = koinViewModel(), id = newId)
        }
    }
}
