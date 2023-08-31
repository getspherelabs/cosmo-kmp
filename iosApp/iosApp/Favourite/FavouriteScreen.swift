import SwiftUI
import shared

struct FavouriteScreen: View {
    
    @ObservedObject
    var favouriteViewModel: FavouriteIosViewModel = FavouriteIosViewModel(viewModel: KoinSwift.shared.koinViewModel())
    
    var body: some View {
        let state = favouriteViewModel.state
        
        NavigationView {
            if #available(iOS 16.0, *) {
                FavouriteItems(newItems: state.favourites)
                    .navigationTitle("My favourites")
                    .navigationBarTitleTextColor(Color.white)
                    .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
                    .toolbarBackground(Color(hex: 0xff0B0B0B), for: .tabBar)
                    .background(Color(hex: 0xff0B0B0B))
            } else {
                // Fallback on earlier versions
            }
            
        }

    }
}

struct FavouriteItems: View {
    var newItems: [FavouriteDomainFavouriteDomain]
    
    var body: some View {
        if #available(iOS 16.0, *) {
            List {
                ForEach(newItems, id: \.id) { item in
                    CosmoCard(title: item.name, description: item.description_,image: "earth")
                }
                
            }
            .background(Color(hex: 0xff0B0B0B))
            .scrollContentBackground(.hidden)
        } else {
            // Fallback on earlier versions
        }
    }
}

struct FavouriteScreen_Previews: PreviewProvider {
    static var previews: some View {
        FavouriteScreen()
    }
}
