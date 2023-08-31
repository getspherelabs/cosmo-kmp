//
//  SearchScreen.swift
//  iosApp
//
//  Created by 1234 on 2023/08/29.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

import SwiftUI
import shared

@available(iOS 15.0, *)
struct SearchScreen: View {
    
    @ObservedObject
    var searchViewModel: SearchIosViewModel = SearchIosViewModel(viewModel: KoinSwift.shared.koinViewModel())
    
    var body:  some View {
        NavigationView {
            if #available(iOS 16.0, *) {
                SearchItems(searchedPlanets: searchViewModel.state.planets)
                    .navigationTitle("Search  planets")
                    .navigationBarTitleTextColor(Color.white)
                    .searchable(
                        text: $searchViewModel.query,
                        placement: .navigationBarDrawer(displayMode: .always)
                    )
                    .onChange(of: searchViewModel.query) { newQuery in
                        searchViewModel.wish(currentWish: SearchWishOnQueryChanged(query: newQuery))
                    }
                    .overlay {
                        if searchViewModel.query.isEmpty && searchViewModel.state.planets.isEmpty {
                            EmptySearchView(query: searchViewModel.query)
                        }
                    }
                    .background(Color(hex: 0xff0B0B0B))
                    .toolbarBackground(Color(hex: 0xff0B0B0B), for: .tabBar)
            } else {
                // Fallback on earlier versions
            }
        }
    }
}

struct SearchItems: View {
    
    var searchedPlanets: [SearchDomainSearchPlanetDomain]

    var body: some View {
        if #available(iOS 16.0, *) {
            List {
                ForEach(searchedPlanets, id: \.id) { planet in
                    CosmoCard(title: planet.name, description: planet.description_, image: "earth")
                }
                
            }
            .background(Color(hex: 0xff0B0B0B))
            .scrollContentBackground(.hidden)
        } else {
            // Fallback on earlier versions
        }
  
    }
}

