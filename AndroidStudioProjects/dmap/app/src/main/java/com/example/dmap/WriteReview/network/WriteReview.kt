package com.example.dmap.WriteReview.network

import com.example.dmap.WriteReview.data.WriteReviewRequest
import com.example.dmap.WriteReview.data.WriteReviewResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface WriteReview {

    @POST("/review")
    @Headers("Content-Type: application/json")
    fun writeReview(@Header("x-access-token") token : String , @Body body : WriteReviewRequest) : Call<WriteReviewResponse>
}