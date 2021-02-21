package com.example.dmap.Signup

import com.example.dmap.Map.network.GetToiletByLocationResponse
import com.example.dmap.Signup.network.SignupRequest
import com.example.dmap.Signup.network.SignupResponse
import retrofit2.Call


class SignupRepository(){

    //외부 데이터
    val remoteData = SignupRemoteData()

    //로컬 데이터

    suspend fun signUpRequest(body : SignupRequest) : Call<SignupResponse> {
        return remoteData.signUp(body)
    }

    suspend fun isUserIdDuplicate(nickName : String) : Call<SignupResponse> {
        return remoteData.isDuplicateNickName(nickName)
    }


}