package com.example.dmap.Signup.data

import com.example.dmap.Map.network.GetToiletByLocationResponse
import com.example.dmap.Signup.network.SignupRequest
import com.example.dmap.Signup.network.SignupResponse
import retrofit2.Call

interface LoginDataSource {

   suspend fun signUp(body : SignupRequest) : Call<SignupResponse>

   suspend fun isDuplicateNickName(nickName : String) : Call<SignupResponse>


}