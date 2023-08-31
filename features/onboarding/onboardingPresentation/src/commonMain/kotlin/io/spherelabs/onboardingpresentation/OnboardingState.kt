package io.spherelabs.onboardingpresentation

data class OnboardingState(
    val name: String = "",
    val isFirstTime: Boolean = false
) {
    companion object {
        val Empty = OnboardingState()
    }
}
