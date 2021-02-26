package com.example.dmap.Login

import com.example.dmap.Login.network.KakaoIdResponse
import com.example.dmap.Login.network.LoginInterface
import com.example.dmap.Login.network.LoginResponse
import com.example.dmap.Network.createRetrofit
import com.example.dmap.User.User
import retrofit2.Call

class LoginRepository  {


    val SIGNUP_BASE_URL = "https://kapi.kakao.com"
    var user = User
    private val loginInterface = createRetrofit(LoginInterface::class.java, SIGNUP_BASE_URL)

     fun signUpRequest(token : String) : Call<KakaoIdResponse> {
        return loginInterface.getKakaoId("Bearer {$token}")
    }

    fun userLogin(userId : String , userPassword : String) : Call<LoginResponse> {

        return loginInterface.userLogin(userId, userPassword)
    }

}