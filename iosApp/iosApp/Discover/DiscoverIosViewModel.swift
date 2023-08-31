import Foundation
import shared
import Combine

@MainActor
class DiscoverIosViewModel: ObservableObject {
    
    private let viewModel: DiscoverViewModel
    
    @Published
    private(set) var state: DiscoverState
    let effect: AnyPublisher<DiscoverIosEffect, Never>
    
    init(viewModel: DiscoverViewModel) {
        self.viewModel = viewModel
        
        self.effect = viewModel.effect.asNonNullPublisher()
            .assertNoFailure()
            .map(DiscoverIosEffect.init)
            .eraseToAnyPublisher()
 
        self.state = viewModel.state.value
        
        viewModel.state.bind(scope: viewModel.viewModelScope, values: { [weak self] in self?.state = $0})
        
        self.viewModel.wish(wish: DiscoverWishPopularPlanetsStarted())
        self.viewModel.wish(wish: DiscoverWishArticleStarted())
    }
    
    func wish(currentWish: DiscoverWish) {
        self.viewModel.wish(wish: currentWish)
    }
    
    deinit {
        viewModel.clear()
    }
}
