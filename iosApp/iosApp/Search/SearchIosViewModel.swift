//
//  SearchIosViewModel.swift
//  iosApp
//
//  Created by 1234 on 2023/08/29.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import Combine

@MainActor
class SearchIosViewModel:  ObservableObject {
    
    private let viewModel: SearchViewModel
        
    @Published private(set) var state: SearchState
    let effect: AnyPublisher<IosSearchEffect, Never>
    
    @Published var query: String = ""
    
    @Published var searchQuery = "" {
            didSet {
                self.wish(currentWish: SearchWishOnQueryChanged(query: searchQuery))
            }
        }
    
    init(viewModel: SearchViewModel) {
        self.viewModel = viewModel
        
        self.effect = viewModel.effect.asNonNullPublisher()
            .assertNoFailure()
            .map(IosSearchEffect.init)
            .eraseToAnyPublisher()
 
        self.state = viewModel.state.value
        
        viewModel.state.bind(scope: viewModel.viewModelScope, values: { [weak self] in self?.state = $0})
    }
    
    func wish(currentWish: SearchWish) {
        self.viewModel.wish(wish: currentWish)
    }

    
    deinit {
        viewModel.clear()
    }
}
