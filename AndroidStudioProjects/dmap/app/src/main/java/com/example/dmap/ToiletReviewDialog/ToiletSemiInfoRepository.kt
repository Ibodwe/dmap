package com.example.dmap.ToiletReviewDialog

import com.example.dmap.Network.createRetrofit
import com.example.dmap.ToiletReviewDialog.data.ToiletReviewResponse
import com.example.dmap.ToiletReviewDialog.network.ReviewInterface
import com.example.dmap.User.User
import retrofit2.Call

class ToiletSemiInfoRepository {


    val retrofit = createRetrofit(ReviewInterface::class.java , "http://49.247.0.135:443")


    fun getToiletReviewById(toiletId : String) : Call<ToiletReviewResponse>{
       return  retrofit.getReviewByToiletId(User.userToken!!, toiletId)
    }

}