package com.example.dmap.Map.AddMap.network

import com.example.dmap.Map.AddMap.data.NewToiletRegisterRequest
import com.example.dmap.Map.AddMap.data.NewToiletRegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface NewToilet {

    @POST("/toilet")
    @Headers("Content-Type: application/json")
    fun newToilet(@Header("x-access-token") token : String, @Body body : NewToiletRegisterRequest) : Call<NewToiletRegisterResponse>


}