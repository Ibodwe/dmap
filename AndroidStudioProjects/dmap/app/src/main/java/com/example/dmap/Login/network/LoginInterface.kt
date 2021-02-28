package com.example.dmap.Login.network

import retrofit2.Call
import retrofit2.http.*

interface LoginInterface {

   @GET("/v2/user/me")
   fun getKakaoId(@Header("Authorization") token : String) : Call<KakaoIdResponse>

   @POST("/oauth/kakao/signin")
   @Headers("Content-Type: application/json")
   fun userLogin(@Body body : LoginRequest) : Call<LoginResponse>
}