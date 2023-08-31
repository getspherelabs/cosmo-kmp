import SwiftUI
import shared

struct DiscoverScreen: View {

    @ObservedObject
    var discoverViewModel: DiscoverIosViewModel = DiscoverIosViewModel(viewModel: KoinSwift.shared.koinViewModel())
    
    var body: some View {
        let state = self.discoverViewModel.state
        
        NavigationView {
            if #available(iOS 16.0, *) {
                ScrollView {
                    ZStack {
                        VStack {
                            TopDiscover(url: "", name: "Behzod")
                            FlyWithCardItem {
                                print("Tap")
                            }
                            Spacer().frame(height: 24)
                            
                            PopularTextStars()
                            
                            PopularStars(
                                popularStars: state.popularPlanets,
                                isLoading: state.isLoading,
                                onExplore: { str in print(str)})
                            
                            Spacer().frame(height: 24)
                            
                            Text("Articles")
                                .font(.system(size: 18))
                                .bold()
                                .foregroundColor(.white)
                                .frame(maxWidth: .infinity,alignment: .leading)
                                .padding(.horizontal, 24)
                            
                            ArticleItem(
                                title: state.article?.title ?? "Unknown",
                                author: state.article?.author ?? "Unknown"
                            )
                        }
                    }
                }
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background(Color(hex: 0xff0B0B0B))
                .toolbarColorScheme(.dark, for: .navigationBar)
                .toolbarBackground(Color(hex: 0xff0B0B0B), for: .navigationBar)
                .toolbarBackground(Color(hex: 0xff0B0B0B), for: .tabBar)
            } else {
                // Fallback on earlier versions
            }
    
        }.navigationBarTitleTextColor(Color.white)
        .navigationBarTitle("Discover").foregroundColor(.white)

 
    }
}

struct TopDiscover: View {
    var url: String
    var name: String
    
    var body: some View {
        HStack(spacing: 8) {
            RoundedImage(imageUrl: url)
            Text(name)
                .font(.system(size: 15))
                .foregroundColor(.white)
                .lineLimit(1)
        }
        .padding(.horizontal, 24)
        .padding(.vertical, 8)
        .frame(maxWidth: .infinity, alignment: .trailing)
    }
}

struct RoundedImage: View {
    var imageUrl: String
    
    var body: some View {
        Image(imageUrl)
            .resizable()
            .aspectRatio(contentMode: .fit)
            .frame(width: 40, height: 40)
            .clipShape(Circle())
            .overlay(Circle().stroke(Color.blue,lineWidth: 3))
    }
}

struct PopularTextStars: View {
    var body: some View {
        HStack {
            Text("Popular Stars")
                .font(.system(size: 18))
                .bold()
                .foregroundColor(.white)
            
            Spacer()
            
            Text("View All")
                .font(.system(size: 14))
                .foregroundColor(.white)
        }
        .padding(.horizontal, 24)
        .frame(maxWidth: .infinity, alignment: .leading)
    }
}

struct FlyWithCardItem: View {
    var onFlyClick: () -> Void
    
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 16)
                .fill(Color.black)
                .frame(maxWidth: .infinity, maxHeight: 165)
                .padding(.horizontal, 36)
            
            HStack(spacing: 20) {
                VStack(alignment: .leading) {
                    Text("Fly with\nstars")
                        .foregroundColor(.white)
                        .font(.system(size: 24))
                        .padding(.top, 8)
                        .padding(.leading, 8)
                    Spacer().frame(height: 24)
                    FlyButton(onFlyClick: onFlyClick)
                }
                
                Image("vr_planet")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 150)
            }
            .padding(.horizontal)
        }
    }
}


struct FlyButton: View {
    var onFlyClick: () -> Void
    
    var body: some View {
        Button(action: onFlyClick) {
            Text("Fly")
                .foregroundColor(.white)
                .font(.system(size: 16))
        }
        .frame(width: 93, height: 46)
        .background(Color(hex: 0xff7020c4))
        .cornerRadius(16)
    }
}

// Helper extension to convert hex color to SwiftUI Color
extension Color {
    init(hex: UInt32) {
        let red = Double((hex >> 16) & 0xff) / 255.0
        let green = Double((hex >> 8) & 0xff) / 255.0
        let blue = Double(hex & 0xff) / 255.0
        self.init(red: red, green: green, blue: blue)
    }
}


struct PopularStars: View {
    var popularStars: [DiscoverDomainPlanetDomain]
    var isLoading: Bool
    var onExplore: (String) -> Void
    
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            HStack(spacing: 14) {
                ForEach(popularStars, id: \.id) { item in
                    if isLoading {
                        ProgressView()
                    } else {
                        PopularStarView(
                            name: item.name,
                            distance: item.distanceFromSun
                        ) {
                            onExplore(item.id)
                        }
                    }
                }
            }
            .padding(.horizontal, 14)
            .frame(maxWidth: .infinity, alignment: .leading)
        }
    }
}

struct PopularStarView: View {
    var name: String
    var distance: String
    var onExplore: () -> Void
    
    var body: some View {
            VStack(spacing: 10) {
                HStack {
                    Image("earth")
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(width: 150, height: 150)
                        .clipShape(RoundedRectangle(cornerRadius: 32))
                    
                    VStack(alignment: .leading, spacing: 4) {
                        Text(name)
                            .foregroundColor(.white)
                        Text("0.16")
                            .foregroundColor(.white)
                        Text(distance)
                            .foregroundColor(.white)
                    }
                }
                
                Button(action: onExplore, label: {
                    Text("Explore")
                        .frame(maxWidth: .infinity, maxHeight: 47)
                        .foregroundColor(.white)
                        .background(Color.blue)
                        .cornerRadius(16)
                        .padding(EdgeInsets(top: 0, leading: 24, bottom: 0, trailing: 24))
                })
                Spacer().frame(height: 2)
            }
            .padding(10)
            .background(Color.black)
            .cornerRadius(32)
        
        .frame(width: 300, height: 230)
    }
}



struct ArticleItem: View {
    var title: String
    var author: String
    
    var body: some View {
        HStack {
            Image("earth")
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 150, height: 150)
                .clipShape(RoundedRectangle(cornerRadius: 32))
            VStack {
                Text(title)
                    .foregroundColor(Color.white)
                    .bold()
                    .font(.system(size: 24))
                
                Text(author)
                    .foregroundColor(.white)
                    .font(.system(size: 16))
            }
        }
        .padding(24)
        .background(Color.black)
        .cornerRadius(32)
        .frame(maxWidth: .infinity, maxHeight: 200)

    }
}


struct DiscoverScreen_Previews: PreviewProvider {
    static var previews: some View {
        DiscoverScreen()
    }
}

struct ArticleItem_Preview: PreviewProvider {
    static var previews: some View {
        ArticleItem(title: "", author: "")
    }
}
