package io.spherelabs.cosmokmp.onboarding

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor
import io.spherelabs.onboardingpresentation.OnboardingEffect
import io.spherelabs.onboardingpresentation.OnboardingMiddleware
import io.spherelabs.onboardingpresentation.OnboardingReducer
import io.spherelabs.onboardingpresentation.OnboardingState
import io.spherelabs.onboardingpresentation.OnboardingWish

class OnboardingViewModel(
    private val onboardingReducer: OnboardingReducer,
    private val onboardingMiddleware: OnboardingMiddleware
) : CommonViewModel<OnboardingState, OnboardingWish, OnboardingEffect>() {

    override val store: Store<OnboardingState, OnboardingWish, OnboardingEffect> = createMeteor(
        configs = MeteorConfigs.build {
            initialState = OnboardingState.Empty
            storeName = "OnboardingViewModel"
            reducer = onboardingReducer
            middlewares = listOf(onboardingMiddleware)
        }
    )
}
