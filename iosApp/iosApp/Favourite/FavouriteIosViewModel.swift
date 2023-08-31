import Foundation
import shared
import Combine

@MainActor
class FavouriteIosViewModel: ObservableObject {
    
    private let viewModel: FavouriteViewModel
    
    @Published
    private(set) var state: FavouriteState
    let effect: AnyPublisher<FavouriteIosEffect, Never>
    
    init(viewModel: FavouriteViewModel) {
        self.viewModel = viewModel
        
        self.effect = viewModel.effect.asNonNullPublisher()
            .assertNoFailure()
            .map(FavouriteIosEffect.init)
            .eraseToAnyPublisher()
 
        self.state = viewModel.state.value
        
        viewModel.state.bind(scope: viewModel.viewModelScope, values: { [weak self] in self?.state = $0})
       
    }
    
    func wish(currentWish: FavouriteWish) {
        self.viewModel.wish(wish: currentWish)
    }
    
    deinit {
        viewModel.clear()
    }
}
