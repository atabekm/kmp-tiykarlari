import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private var helper: GithubHelper = GithubHelper()
    
    @State private var query: String = "shagalalab"
    
    let greet = Greeting().greet()
    
    var body: some View {
        VStack {
            Text(greet)
            HStack {
                TextField("Enter your github username", text: $query)
                    .autocorrectionDisabled()
                    .textInputAutocapitalization(.never)
                    .textFieldStyle(.roundedBorder)
                Button("Search repos") {
                    helper.fetchRepos(for: query)
                }
                .buttonStyle(.borderedProminent)
            }
            .padding(.horizontal, 16)
            List(helper.userRepos, id: \.self) { repo in
                VStack(alignment: .leading) {
                    Text(repo.name)
                        .bold()
                    if let desc = repo.description_ {
                        Text(desc)
                    }
                }
            }.listStyle(PlainListStyle())
        }
    }
}
