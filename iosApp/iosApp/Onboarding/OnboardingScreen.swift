//
//  OnboardingScreen.swift
//  iosApp
//
//  Created by 1234 on 2023/08/29.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared
import NavigationStack

@available(iOS 15.0, *)
struct OnboardingScreen: View {
    
    @ObservedObject
    var viewModel: OnboardingIosViewModel = OnboardingIosViewModel(viewModel: KoinSwift.shared.koinViewModel())
    
    @EnvironmentObject private var navigationStack: NavigationStackCompat
    
    var body: some View {
        NavigationView {
            ZStack(alignment: .topLeading) {
                Color.black.edgesIgnoringSafeArea(.all) // Background color
                
                VStack(alignment: .leading, spacing: 24) {
                    Text("Explore the universe")
                        .foregroundColor(.white)
                        .font(.system(size: 48))
                        .bold()
                        .padding(.leading, 24)
                    Button(action: {
                        self.viewModel.wish(currentWish: OnboardingWishGetStartedClick())
                    }, label: {
                        Text("Get Started")
                            .frame(maxWidth: .infinity, maxHeight: 47)
                            .foregroundColor(.black)
                            .background(Color.white)
                            .cornerRadius(16)
                            .padding(EdgeInsets(top: 0, leading: 24, bottom: 0, trailing: 24))
                    })
                    
                    
                    LottieView(lottieFile: "space_anim")
                        .frame(maxWidth: .infinity)
                }
                .padding(.top, 24)
                .padding(.horizontal, 24)
                .foregroundColor(.white)
            }
        }
        .navigationViewStyle(StackNavigationViewStyle())
        .onReceive(self.viewModel.effect) { effect in
            observeEffect(effect: effect)
        }
    }
    
    private func observeEffect(effect: IosOnboardingEffect) {
        switch effect {
        case .signUp, .signIn:
            self.navigationStack.navigateToMain()
        case .isFirstTime, .isFinished, .failure:
            print("")
        }
        
    }
}




