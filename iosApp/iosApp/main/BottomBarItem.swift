//
//  BottomBarItem.swift
//  iosApp
//
//  Created by 1234 on 2023/08/06.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

public struct BottomBarItem {
    public let icon: Image
    public let title: String
    public let color: Color
    
    public init(icon: Image,
                title: String,
                color: Color){
        self.icon = icon
        self.title = title
        self.color = color
    }
    
    public init(icon: String,
                title: String,
                color: Color) {
        self = BottomBarItem(icon: Image(systemName: icon),
                             title: title,
                             color: color)
    }
}
