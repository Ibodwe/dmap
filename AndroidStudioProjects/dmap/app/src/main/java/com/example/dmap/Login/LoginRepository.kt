package com.example.dmap.Login

import com.example.dmap.Login.network.KakaoIdResponse
import com.example.dmap.Login.network.LoginInterface
import com.example.dmap.Network.createRetrofit
import retrofit2.Call

class LoginRepository  {


    val SIGNUP_BASE_URL = "https://kapi.kakao.com"

    private val loginInterface = createRetrofit(LoginInterface::class.java, SIGNUP_BASE_URL)

    suspend fun signUpRequest(token : String) : Call<KakaoIdResponse> {
        return loginInterface.getKakaoId("Bearer {$token}")
    }

}