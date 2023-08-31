//
//  OnboardingIosViewModel.swift
//  iosApp
//
//  Created by 1234 on 2023/08/29.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import Combine

@MainActor
class OnboardingIosViewModel: ObservableObject {
    private let viewModel: OnboardingViewModel
    
    @Published private(set) var state: OnboardingState
    let effect: AnyPublisher<IosOnboardingEffect, Never>
    
    init(viewModel: OnboardingViewModel) {
        self.viewModel = viewModel
        self.state = viewModel.state.value
        self.effect = viewModel.effect.asNonNullPublisher()
            .assertNoFailure()
            .map(IosOnboardingEffect.init)
            .eraseToAnyPublisher()
 
        self.state = viewModel.state.value
        
        viewModel.state.bind(scope: viewModel.viewModelScope, values: { [weak self] in self?.state = $0})
    }
    
    func wish(currentWish: OnboardingWish) {
        self.viewModel.wish(wish: currentWish)
    }

    
    deinit {
        viewModel.clear()
    }
}


