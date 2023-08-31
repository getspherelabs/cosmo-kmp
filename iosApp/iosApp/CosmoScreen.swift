import SwiftUI
import NavigationStack

@available(iOS 15.0, *)
struct CosmoScreen: View {
    var body: some View {
        NavigationStackView(transitionType: .custom(.scale)) {
            OnboardingScreen()
        }
    }
}

@available(iOS 15.0, *)
struct CosmoScreen_Previews: PreviewProvider {
    static var previews: some View {
        CosmoScreen()
    }
}
