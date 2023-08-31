import Foundation
import shared

public enum DiscoverIosEffect {

  case failure(DiscoverEffectFailure)

  public var sealed: DiscoverEffect {
    switch self {
    case .failure(let obj):
      return obj as! shared.DiscoverEffect
    }
  }

  public init(_ obj: DiscoverEffect) {
    if let obj = obj as? shared.DiscoverEffectFailure {
      self = .failure(obj)
    } else {
      fatalError("DiscoverIosEffect not synchronized with DiscoverEffect class")
    }
  }

}
