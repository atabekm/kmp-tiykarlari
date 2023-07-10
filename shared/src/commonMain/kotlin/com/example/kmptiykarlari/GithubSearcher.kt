package com.example.kmptiykarlari

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class GithubSearcher {
    private val token: String = ""

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend fun getUserRepos(username: String): List<UserRepo> {
        return client
            .get("https://api.github.com/users/$username/repos") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
            }
            .body()
    }
}

@Serializable
data class UserRepo(
    val name: String,
    val description: String? = null,
)