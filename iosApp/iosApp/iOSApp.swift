//
//  iOSApp.swift
//  iosApp
//
//  Created by 1234 on 2023/08/30.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

@available(iOS 15.0, *)
@main
struct iOSApp: App {
        
        init() {
            KoinSwift.shared.initKoin()
        }

        var body: some Scene {
            WindowGroup {
                CosmoScreen()
            }
        }
}
