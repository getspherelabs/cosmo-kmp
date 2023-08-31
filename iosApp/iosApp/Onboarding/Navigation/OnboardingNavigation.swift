import Foundation
import NavigationStack

@available(iOS 15.0, *)
extension NavigationStackCompat {
    
    func navigateToMain() {
        self.push(MainScreen())
    }
}
