//
//  BarBuilder.swift
//  iosApp
//
//  Created by 1234 on 2023/08/06.
//  Copyright © 2023 orgName. All rights reserved.
//


import Foundation
@resultBuilder
public struct BarBuilder{}


public extension BarBuilder{
    
    
    static func buildBlock(_ items: BottomBarItem...) -> [BottomBarItem]{
        items
    }
    
    
}
