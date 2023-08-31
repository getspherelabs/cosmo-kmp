import shared

public enum IOSArticleDetailEffect {

    case failure(ArticleDetailEffectFailure)

  public var sealed: ArticleDetailEffect {
    switch self {
    case .failure(let obj):
      return obj as! shared.ArticleDetailEffect
    }
  }

  public init(_ obj: ArticleDetailEffect) {
    if let obj = obj as? shared.ArticleDetailEffectFailure {
      self = .failure(obj)
    } else {
      fatalError("ArticleDetailEffectKs not synchronized with ArticleDetailEffect class")
    }
  }

}
