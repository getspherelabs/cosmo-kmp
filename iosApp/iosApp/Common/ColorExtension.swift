//
//  ColorExtension.swift
//  iosApp
//
//  Created by 1234 on 2023/08/29.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

extension Color {
    init(hex: UInt, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
}


extension Color {
    static let darkGrayHex = Color(
        red: Double(0x0B) / 255.0,
        green: Double(0x0B) / 255.0,
        blue: Double(0x0B) / 255.0
    )
}
