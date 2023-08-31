
import Foundation

@resultBuilder
public struct BarBuilder{}


public extension BarBuilder{
    
    static func buildBlock(_ items: BottomBarItem...) -> [BottomBarItem]{
        items
    }
    
}

