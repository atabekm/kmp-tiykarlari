package com.example.kmptiykarlari

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform