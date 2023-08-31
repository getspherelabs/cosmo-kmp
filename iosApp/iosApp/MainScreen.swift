//
//  MainScreen.swift
//  iosApp
//
//  Created by 1234 on 2023/08/29.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

@available(iOS 15.0, *)
struct MainScreen: View {
    
    var body: some View {
        if #available(iOS 16.0, *) {
            TabView {
                
                SearchScreen()
                    .tabItem {
                        Label("Search",
                              systemImage: "person.crop.square.fill.and.at.rectangle")
                    }
                
                DiscoverScreen()
                    .tabItem {
                        Label("Discover", systemImage: "note")
                    }
                
                FavouriteScreen()
                    .tabItem {
                        Label(
                            "Favourite",
                            systemImage: "person.crop.square.fill.and.at.rectangle"
                        )
                    }
                
                AboutScreen()
                    .tabItem {
                        Label(
                            "About",
                            systemImage: "person.crop.square.fill.and.at.rectangle"
                        )
                    }
            }
            .toolbarBackground(Color(hex: 0xff0B0B0B), for: .tabBar)
            
        } else {
            // Fallback on earlier versions
        }
            
    }
}

