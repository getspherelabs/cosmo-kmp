package io.spherelabs.cosmokmp.android.ui.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import io.spherelabs.cosmokmp.android.common.animatedComposable
import io.spherelabs.cosmokmp.android.ui.onboarding.OnboardingRoute

const val onboardingRoute = "onboarding"

fun NavController.navigateToOnboarding() {
    this.navigate(onboardingRoute)
}

fun NavGraphBuilder.onboardingScreen(
    navigateToSignUp: () -> Unit
) {
    animatedComposable(onboardingRoute) {
        OnboardingRoute(navigateToSignUp = { navigateToSignUp.invoke() })
    }
}
