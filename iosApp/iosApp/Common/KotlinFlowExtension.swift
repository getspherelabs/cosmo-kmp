//
//  KotlinFlowExtension.swift
//  iosApp
//
//  Created by 1234 on 2023/08/29.
//  Copyright © 2023 orgName. All rights reserved.
//


import Foundation
import shared
import Combine


extension Flow {
    func asNonNullPublisher<T: AnyObject>(_ type: T.Type = T.self) -> AnyPublisher<T, Error> {
        NonNullFlowPublisher(flow: self).eraseToAnyPublisher()
    }
    
    func asNullablePublisher<T: AnyObject>(_ type: T.Type = T.self) -> AnyPublisher<T?, Error> {
        NullableFlowPublisher(flow: self).eraseToAnyPublisher()
    }
}

private func unconfinedScope() -> CoroutineScope {
    CoroutineScopeKt.CoroutineScope(
        context: Dispatchers.shared
            .Unconfined
            .plus(context: SupervisorKt.SupervisorJob(parent: nil))
    )
}

private struct NonNullFlowPublisher<T: AnyObject>: Publisher {
  typealias Output = T
  typealias Failure = Error

  private let flow: Flow

  init(flow: Flow) {
    self.flow = flow
  }

  func receive<S>(subscriber: S) where S: Subscriber, Error == S.Failure, T == S.Input {
    let subscription = NonNullFlowSubscription(
      flow: flow,
      subscriber: subscriber
    )
    subscriber.receive(subscription: subscription)
  }
}



private class NonNullFlowSubscription<T: AnyObject, S: Subscriber>: Subscription where S.Input == T, S.Failure == Error {

  private var subscriber: S?
  private var closable: Closeable?

  init(
    flow: Flow,
    subscriber: S
  ) {
    self.subscriber = subscriber

    let wrapper = NonNullCommonFlowKt.asCommonFlow(flow) as! NonNullCommonFlow<T>
    self.closable = wrapper.bind(
      scope: unconfinedScope(),
      values: {
        _ = subscriber.receive($0)
      },
      failure: {
        subscriber.receive(completion: .failure($0.asNSError()))
      },
      completion: {
        subscriber.receive(completion: .finished)
      }
    )
  }

  func request(_ demand: Subscribers.Demand) { }

  func cancel() {
    self.subscriber = nil

    self.closable?.close()
    self.closable = nil
  }
}


private struct NullableFlowPublisher <AO: AnyObject> : Publisher {
    typealias Output = AO?
    typealias Failure = Error
    
    private let flow: Flow
    
    init(flow: Flow) {
        self.flow = flow
    }
    
    func receive<S>(subscriber: S) where S: Subscriber, Error == S.Failure, AO? == S.Input {
        let subscription = NullableFlowSubsription(
            flow: flow,
            subscriber: subscriber
        )
        
        subscriber.receive(subscription: subscription)
    }
}

private class NullableFlowSubsription <T: AnyObject, S: Subscriber>: Subscription where S.Input == T?, S.Failure == Error{

    private var subscriber: S?
    private var closeable: Closeable?

    init(flow: Flow, subscriber: S) {
        self.subscriber = subscriber
        let wrapper = NullableCommonFlowKt.asCommonFlow(flow) as! NullableCommonFlow<T>
        self.closeable = wrapper.bind(
            scope: unconfinedScope(),
            values: {
                _ = subscriber.receive($0)
            },
            failure: {
                subscriber.receive(completion: .failure($0.asNSError()))
            },
            completion: {
                subscriber.receive(completion: .finished)
            }
        )
    }
    
    func request(_ demand: Subscribers.Demand) { }
    
    func cancel() {
        self.subscriber = nil
    
        self.closeable?.close()
        self.closeable = nil
      }
}

