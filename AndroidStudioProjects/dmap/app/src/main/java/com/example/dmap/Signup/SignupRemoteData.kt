package com.example.dmap.Signup

import com.example.dmap.Map.network.GetToiletByLocationResponse
import com.example.dmap.Network.createRetrofit
import com.example.dmap.Signup.data.LoginDataSource
import com.example.dmap.Signup.network.SignupInterface
import com.example.dmap.Signup.network.SignupRequest
import com.example.dmap.Signup.network.SignupResponse
import retrofit2.Call

class SignupRemoteData : LoginDataSource {

    val SIGNUP_BASE_URL = "http://49.247.0.135:443/"

     private val signupInterface = createRetrofit(SignupInterface::class.java, SIGNUP_BASE_URL)

    override suspend fun signUp(body: SignupRequest): Call<SignupResponse> {
        return signupInterface.signUp(body)
    }

    override suspend fun isDuplicateNickName(nickName: String): Call<SignupResponse> {
        return signupInterface.isDuplicateId(nickName)
    }



}