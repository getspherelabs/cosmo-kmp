//
//  MainScreen.swift
//  iosApp
//
//  Created by 1234 on 2023/08/06.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MainScreen: View {
    
    var body: some View {
        TabView {
            SearchScreen()
                .tabItem {
                    Label("Search", systemImage: "person.crop.square.fill.and.at.rectangle")
                }
            DiscoverScreen()
                .tabItem {
                    Label("Discover", systemImage: "note")
                }
            FavouriteScreen()
                .tabItem {
                    Label("Favourite", systemImage: "person.crop.square.fill.and.at.rectangle")
                }
            
            AboutScreen()
                .tabItem {
                    Label("About", systemImage: "person.crop.square.fill.and.at.rectangle")
                }
        }
    }
}

struct MainScreen_Previews: PreviewProvider {
    static var previews: some View {
        MainScreen()
    }
}
