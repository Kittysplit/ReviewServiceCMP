package com.kittysplit.reviewservicecmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform