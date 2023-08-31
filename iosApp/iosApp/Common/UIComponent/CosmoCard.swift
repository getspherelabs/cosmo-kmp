//
//  CosmoCard.swift
//  iosApp
//
//  Created by 1234 on 2023/08/29.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CardModifier: ViewModifier {
    func body(content: Content) -> some View {
        content
            .cornerRadius(20)
            .shadow(color: Color.black.opacity(0.2), radius: 20, x: 0, y: 0)
    }
    
}

struct CosmoCard: View {
    var title: String
    var description: String
    var image: String
    var color: Color = Color.black
       
    var body: some View {
           HStack(alignment: .center) {
               Image(image)
                   .resizable()
                   .aspectRatio(contentMode: .fit)
                   .frame(width: 100)
                   .padding(.all, 20)
               
               VStack(alignment: .leading) {
                   Text(title)
                       .font(.system(size: 26, weight: .bold, design: .default))
                       .foregroundColor(.white)
                   Text(description)
                       .font(.system(size: 16, weight: .bold, design: .default))
                       .foregroundColor(.gray)
                       .lineLimit(4)
            
               }.padding(.trailing, 20)
               Spacer()
           }
           .frame(maxWidth: .infinity, alignment: .center)
           .background(Color.darkGrayHex)
           .modifier(CardModifier())
       }
}

struct CosmoCard_Previews: PreviewProvider {
    static var previews: some View {
        ContentPrev().background(Color.black)
    }
}

struct ContentPrev: View {
    
    var body: some View {
        CosmoCard(title: "Mars", description: "Blah Blah Blah Blah Blah Blah Blah Blah Blah",image: "earth")
    }
}

