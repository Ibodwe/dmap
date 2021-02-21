package com.example.dmap.Signup.network

data class SignupRequest(
    val id: String,
    val password: String,
    val profileIcon: String,
    val nic_name: String,
    val gender: Int,
    val is_oauth : Int
)