package com.compose.session

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform