package com.example.dmap.ToiletReviewDialog.network

import com.example.dmap.ToiletReviewDialog.data.ToiletReviewResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ReviewInterface {

    @GET("user/reviews/{toilet_id}")
    @Headers("Content-Type: application/json")
    fun getReviewByToiletId(@Header("x-access-token") header : String, @Query("toilet_id") toiletId : String) : Call<ToiletReviewResponse>
}