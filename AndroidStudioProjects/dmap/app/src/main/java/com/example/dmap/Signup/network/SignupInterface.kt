package com.example.dmap.Signup.network

import com.example.dmap.Map.network.GetToiletByLocationResponse
import retrofit2.Call
import retrofit2.http.*

interface SignupInterface {

    @POST("local/signup")
    fun signUp(@Body body : SignupRequest) : Call<SignupResponse>

    @GET("/local/signup/{id}")
    fun isDuplicateId(@Query("id") nick : String) : Call<SignupResponse>
}