package io.spherelabs.cosmokmp.android.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import io.spherelabs.cosmokmp.android.ui.cosmo.navigation.cosmoRoute
import io.spherelabs.cosmokmp.android.ui.cosmo.navigation.cosmoScreen
import io.spherelabs.cosmokmp.android.ui.cosmo.navigation.mainScreen
import io.spherelabs.cosmokmp.android.ui.cosmo.navigation.navigateToMain
import io.spherelabs.cosmokmp.android.ui.onboarding.navigation.navigateToOnboarding
import io.spherelabs.cosmokmp.android.ui.onboarding.navigation.onboardingScreen
import io.spherelabs.cosmokmp.onboarding.OnboardingViewModel
import io.spherelabs.onboardingpresentation.OnboardingEffect
import io.spherelabs.onboardingpresentation.OnboardingWish
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CosmoNavHost(
    viewModel: OnboardingViewModel = koinViewModel(),
    navController: NavHostController = rememberAnimatedNavController()
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = cosmoRoute
    ) {
        cosmoScreen {
            LaunchedEffect(true) {
                viewModel.wish(OnboardingWish.CheckFirstTime)

                viewModel.effect.collectLatest {
                    when (it) {
                        OnboardingEffect.IsFinished -> navController.navigateToMain()
                        OnboardingEffect.IsFirstTime -> navController.navigateToOnboarding()
                        else -> {}
                    }
                }
            }
        }
        onboardingScreen(navigateToSignUp = navController::navigateToMain)
        mainScreen()
    }
}
