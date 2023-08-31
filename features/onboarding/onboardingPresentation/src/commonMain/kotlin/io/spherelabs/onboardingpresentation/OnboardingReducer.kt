package io.spherelabs.onboardingpresentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.route
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class OnboardingReducer : Reducer<OnboardingState, OnboardingWish, OnboardingEffect> {

    override fun reduce(
        state: OnboardingState,
        wish: OnboardingWish
    ): Change<OnboardingState, OnboardingEffect> {
        return when (wish) {
            is OnboardingWish.Failed -> {
                effect { OnboardingEffect.Failure(wish.message) }
            }
            OnboardingWish.GetStarted -> {
                route { OnboardingEffect.SignUp }
            }
            is OnboardingWish.IsFinished -> {
                if (wish.value) {
                    route { OnboardingEffect.IsFinished }
                } else {
                    route { OnboardingEffect.IsFirstTime }
                }
            }
            else -> unexpected { state }
        }
    }
}
