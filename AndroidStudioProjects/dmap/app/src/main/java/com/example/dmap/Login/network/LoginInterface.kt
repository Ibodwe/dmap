package com.example.dmap.Login.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface LoginInterface {

   @GET("/v2/user/me")
   fun getKakaoId(@Header("Authorization") token : String) : Call<KakaoIdResponse>

   @GET("/local/signin")
   fun userLogin(userId : String , userPassword : String) : Call<LoginResponse>
}