//
//  IOSOnboardingEffecgt.swift
//  iosApp
//
//  Created by 1234 on 2023/08/28.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

public enum IosOnboardingEffect: Equatable {

  case failure(OnboardingEffectFailure)
  case isFinished
  case isFirstTime
  case signIn
  case signUp

  public var sealed: OnboardingEffect {
    switch self {
    case .failure(let obj):
      return obj as! shared.OnboardingEffect
    case .isFinished:
      return shared.OnboardingEffectIsFinished() as shared.OnboardingEffect
    case .isFirstTime:
      return shared.OnboardingEffectIsFirstTime() as shared.OnboardingEffect
    case .signIn:
      return shared.OnboardingEffectSignIn() as shared.OnboardingEffect
    case .signUp:
      return shared.OnboardingEffectSignUp() as shared.OnboardingEffect
    }
  }

  public init(_ obj: OnboardingEffect) {
    if let obj = obj as? shared.OnboardingEffectFailure {
      self = .failure(obj)
    } else if obj is shared.OnboardingEffectIsFinished {
      self = .isFinished
    } else if obj is shared.OnboardingEffectIsFirstTime {
      self = .isFirstTime
    } else if obj is shared.OnboardingEffectSignIn {
      self = .signIn
    } else if obj is shared.OnboardingEffectSignUp {
      self = .signUp
    } else {
      fatalError("OnboardingEffectKs not synchronized with OnboardingEffect class")
    }
  }

}
