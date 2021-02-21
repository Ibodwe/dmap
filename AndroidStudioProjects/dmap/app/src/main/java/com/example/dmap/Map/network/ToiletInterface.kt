package com.example.dmap.Map.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ToiletInterface {

    @GET("/toilets")
    @Headers("x-access-token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ0ZXN0MTIzIiwiaWF0IjoxNjEyMTEyNDg1LCJleHAiOjE2NDM2NDg0ODUsImlzcyI6ImRkbWFwIn0.Uqdid9le8NvcOzDCEPWvl5eotr4pd9RZuvISHskb-R4")
    fun getToiletByLocation(@Query("lat") lat: Double, @Query("lon") lon: Double) : Call<GetToiletByLocationResponse>

}