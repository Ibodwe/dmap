package com.example.dmap.Map.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ToiletInterface {

    @GET("/toilets")
    @Headers("Content-Type: application/json")
    fun getToiletReviewByLocation(@Header("x-access-token") header : String ,
                                  @Query("lat") lat: Double, @Query("lon") lon: Double) : Call<GetToiletByLocationResponse>

}