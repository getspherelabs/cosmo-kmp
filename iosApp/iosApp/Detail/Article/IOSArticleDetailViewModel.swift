import Foundation
import shared
import Combine

@MainActor
class IOSArticleDetailViewModel: ObservableObject {
    private let viewModel: ArticleDetailViewModel
    
    @Published
    private(set) var state: ArticleDetailState
    let effect: AnyPublisher<IOSArticleDetailEffect, Never>
    
    init(viewModel: ArticleDetailViewModel) {
        self.viewModel = viewModel
        
        self.state = viewModel.state.value
        
        self.effect = viewModel.effect.asNonNullPublisher()
            .assertNoFailure()
            .map(IOSArticleDetailEffect.init)
            .eraseToAnyPublisher()
        
        viewModel.state.bind(
            scope: viewModel.viewModelScope,
            values: { [weak self] in self?.state = $0}
        )
    }
    
    func wish(currentWish: ArticleDetailWish) {
        self.viewModel.wish(wish: currentWish)
    }
    
    deinit {
        viewModel.clear()
    }
}

