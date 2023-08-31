//
//  ArticleDetailScreen.swift
//  iosApp
//
//  Created by 1234 on 2023/08/31.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ArticleDetailScreen: View {
    
    @ObservedObject
    var articleDetailViewModel: IOSArticleDetailViewModel = IOSArticleDetailViewModel(viewModel: KoinSwift.shared.koinViewModel())
    
    var body: some View {
        let state = articleDetailViewModel.state
        
        let newTitle = state.article?.title ?? "Unknown"
        let newId = state.article?.id ?? "Unknown"
        let newAuthor = state.article?.author ?? "Author"
        let newDescription = state.article?.description ?? "Unknown"
        let newCreatedTimestamp = state.article?.createdTimestamp
        let newSize = "10"
        let newDistanceFromSun = "1000km"
        let newIsFavourite = state.isFavourite
        
        let newFavourite = DetailDomainFavouriteDetailDomain(
            id: newId, name: newTitle,
            description: newDescription, size: newSize,
            distanceFromSun: newDistanceFromSun,
            isFavourite: newIsFavourite,
            createdTimestamp: newCreatedTimestamp?.description ?? "Unknown")
        
        NavigationView {
            if #available(iOS 16.0, *) {
                ZStack {
                    VStack {
                        BackButton().frame(alignment: .topLeading)
                        ArticleDetailContent(
                            title: newTitle,
                            description: newDescription,
                            isFavourite: newIsFavourite,
                            onFavouriteClick: {
                                articleDetailViewModel.wish(currentWish: ArticleDetailWishInsertFavourite(favourite: newFavourite))
                                
                            },
                            onDeleteFavouriteClick: {
                                articleDetailViewModel.wish(currentWish:
                                                                ArticleDetailWishDeleteFavourite(id: newId)
                                )
                            })
                    }
                }
                .frame(maxWidth: .infinity, maxHeight: .infinity,alignment: .top)
                .background(Color(hex: 0xff0B0B0B))
                .toolbarColorScheme(.dark, for: .navigationBar)
                .toolbarBackground(Color(hex: 0xff0B0B0B), for: .navigationBar)
                .toolbarBackground(Color(hex: 0xff0B0B0B), for: .tabBar)
            } else {
                // Fallback on earlier versions
            }
               
            
        }
      
        
      
    }
}

struct ArticleDetailContent: View {
    let title: String
    let description: String
    var isFavourite: Bool
    var onFavouriteClick: () -> Void
    var onDeleteFavouriteClick: () -> Void
    
    var body: some View {
        NavigationView {
            VStack {
                Spacer().frame(height: 16)
                
                Image("earth")
                    .resizable()
                    .frame(width: 150, height: 150)
                    .aspectRatio(contentMode: .fit)
                
                Spacer().frame(height: 32)
                
                RoundedDetailContent(
                    title: self.title,
                    description: self.description,
                    isFavourite: self.isFavourite,
                    onFavouriteClick: self.onFavouriteClick,
                    onDeleteFavouriteClick: self.onDeleteFavouriteClick)
           
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity,alignment: .top)
            .background(Color.black)
        }
    }
    
}

struct RoundedDetailContent: View {
    let title: String
    let description: String
    var isFavourite: Bool
    var onFavouriteClick: () -> Void
    var onDeleteFavouriteClick: () -> Void
    
    var body: some View {
        VStack {
            TopDetailContent(
                title: self.title,
                isFavourite: self.isFavourite,
                onFavouriteClick:self.onFavouriteClick,
                onDeleteFavouriteClick: self.onDeleteFavouriteClick)
            Spacer().frame(height: 32)
            Text(description)
                .foregroundColor(.white)
                .padding(24)
                .frame(maxWidth: .infinity, maxHeight: .infinity,alignment: .top)
        }
        .frame(maxWidth: .infinity, maxHeight:.infinity, alignment:.top)
        .background(RoundedRectangle(cornerRadius:  24)
            .fill(Color.darkGrayHex))
    }
}

struct TopDetailContent: View {
    let title: String
    var isFavourite: Bool
    var onFavouriteClick: () -> Void
    var onDeleteFavouriteClick: () -> Void
    
    var body: some View {
        HStack {
            Text(title)
                .foregroundColor(.white)
                .font(.title)
                .fontWeight(.bold)
                .frame(alignment: .topLeading)
            
            Spacer()
            
            Button(action: {
                if isFavourite {
                    onDeleteFavouriteClick()
                } else {
                    onFavouriteClick()
                }
            }) {
                Image(systemName: isFavourite ? "heart.fill" : "heart")
                    .resizable()
                    .frame(width: 24, height: 24)
                    .foregroundColor(isFavourite ? .red : .white)
            }
        }
        .padding(24)
        .frame(maxWidth: .infinity, alignment: .leading)
    }
}

struct BackButton: View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    var body: some View {
        VStack {
            Button(action : {
                self.presentationMode.wrappedValue.dismiss()
            }) {
                Text("Back")
            }
            .navigationBarTitle("")
            .navigationBarBackButtonHidden(true)
            .navigationBarHidden(true)
        }
    }
}



struct ArticleDetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        ArticleDetailScreen()
    }
}
