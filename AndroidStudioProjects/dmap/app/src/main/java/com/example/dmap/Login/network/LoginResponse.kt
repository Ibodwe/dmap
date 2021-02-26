package com.example.dmap.Login.network

data class LoginResponse(
    val `data`: Data
)

data class Data(
    val message: String,
    val token: String
)