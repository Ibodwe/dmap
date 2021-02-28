package com.example.dmap.WriteReview.data

import com.example.dmap.Network.createRetrofit
import com.example.dmap.User.User
import com.example.dmap.WriteReview.network.WriteReview
import retrofit2.Call
import retrofit2.Retrofit

class WriteReviewRepository{

    val retrofit = createRetrofit(WriteReview::class.java , "http://49.247.0.135:443")


    fun registerReview(data : WriteReviewRequest) : Call<WriteReviewResponse> {
        return retrofit.writeReview(User.userToken ?: "noToken" , data)
    }


}