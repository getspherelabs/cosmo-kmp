import Foundation
import shared

extension KoinSwift {
  
    func koinViewModel<T>(
        for type: T.Type = T.self,
        qualifier: Koin_coreQualifier? = nil,
        parameters: (() -> Koin_coreParametersHolder)? = nil) -> T {
            self.get(
                type: type,
                qualifier: qualifier,
                parameters: parameters
            ) as! T
    }
    
    func initKoin() {
        KoinKt.doInitKoin()
    }
}

