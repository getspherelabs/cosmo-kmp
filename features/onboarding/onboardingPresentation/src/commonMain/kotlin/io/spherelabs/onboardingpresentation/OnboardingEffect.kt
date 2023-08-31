package io.spherelabs.onboardingpresentation

sealed interface OnboardingEffect {
    object SignIn : OnboardingEffect
    object SignUp : OnboardingEffect
    data class Failure(val msg: String) : OnboardingEffect
    object IsFirstTime : OnboardingEffect
    object IsFinished : OnboardingEffect
}
