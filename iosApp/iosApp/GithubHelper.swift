//
//  GithubHelper.swift
//  iosApp
//
//  Created by Atabek on 10/7/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

class GithubHelper: ObservableObject {
    @Published var userRepos: Array<UserRepo> = []
    
    func fetchRepos(for username: String) {
        GithubSearcher().getUserRepos(username: username) { repos, error in
            DispatchQueue.main.async {
                if let repos = repos {
                    self.userRepos = repos
                } else {
                    self.userRepos = []
                }
            }
        }
    }
}
