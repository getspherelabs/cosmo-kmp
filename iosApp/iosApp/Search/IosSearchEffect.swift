//
//  SearchIosEffect.swift
//  iosApp
//
//  Created by 1234 on 2023/08/29.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared


public enum IosSearchEffect {

 case failure(SearchEffectFailure)

  public var sealed: SearchEffect {
    switch self {
    case .failure(let obj):
        return obj 
    }
  }

  public init(_ obj: SearchEffect) {
    if let obj = obj as? shared.SearchEffectFailure {
      self = .failure(obj)
    } else {
      fatalError("IosSearchEffect not synchronized with SearchEffect class")
    }
  }

}
