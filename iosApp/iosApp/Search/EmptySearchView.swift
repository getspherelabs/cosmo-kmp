//
//  EmptySearchView.swift
//  iosApp
//
//  Created by 1234 on 2023/08/30.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI


struct EmptySearchView: View {
    let query: String
    
    var body: some View {
        VStack {
            Image(systemName: "exclamationmark.bubble.circle.fill")
                .resizable()
                .frame(width: 64, height: 64)
                .padding()
                .foregroundColor(.black)
            
            Text("Sorry, we couldn't find planets")
                .foregroundColor(.secondary)
                .multilineTextAlignment(.center)
        }
    }
}

struct EmptySearchView_Previews: PreviewProvider {
    static var previews: some View {
        EmptySearchView(query: "Mars")
    }
}
