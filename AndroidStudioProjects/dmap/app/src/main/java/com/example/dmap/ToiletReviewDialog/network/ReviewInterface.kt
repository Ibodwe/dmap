package com.example.dmap.ToiletReviewDialog.network

import com.example.dmap.ToiletReviewDialog.data.ToiletReviewResponse
import retrofit2.Call
import retrofit2.http.*

interface ReviewInterface {

    @GET("toilet/reviews/{toiletId}")
    @Headers("Content-Type: application/json")
    fun getReviewByToiletId(@Header("x-access-token") header : String, @Path("toiletId") toiletId : String) : Call<ToiletReviewResponse>

}