package com.example.dmap.Login

import com.example.dmap.Login.network.KakaoIdResponse
import com.example.dmap.Login.network.LoginInterface
import com.example.dmap.Login.network.LoginRequest
import com.example.dmap.Login.network.LoginResponse
import com.example.dmap.Network.createRetrofit
import com.example.dmap.User.User
import retrofit2.Call

class LoginRepository  {


    val SIGNUP_BASE_URL = "https://kapi.kakao.com"
    var user = User
    private val kakaologinInterface = createRetrofit(LoginInterface::class.java, SIGNUP_BASE_URL)
    private val loginInterface =  createRetrofit(LoginInterface::class.java , "http://49.247.0.135:443")

     fun signUpRequest(token : String) : Call<KakaoIdResponse> {
        return kakaologinInterface.getKakaoId("Bearer {$token}")
    }

    fun userLogin(id : String) : Call<LoginResponse> {
        return loginInterface.userLogin(LoginRequest(id))
    }

}