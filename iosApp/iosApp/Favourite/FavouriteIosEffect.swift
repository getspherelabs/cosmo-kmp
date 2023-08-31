import Foundation
import shared

public enum FavouriteIosEffect {

  case failure(FavouriteEffectFailure)

  public var sealed: FavouriteEffect {
    switch self {
    case .failure(let obj):
      return obj as! shared.FavouriteEffect
    }
  }

  public init(_ obj: FavouriteEffect) {
    if let obj = obj as? shared.FavouriteEffectFailure {
      self = .failure(obj)
    } else {
      fatalError("FavouriteIosEffect not synchronized with FavouriteEffect class")
    }
  }

}
