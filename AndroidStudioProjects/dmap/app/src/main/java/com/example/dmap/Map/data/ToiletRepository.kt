package com.example.dmap.Map.data

import com.example.dmap.Login.network.KakaoIdResponse
import com.example.dmap.Login.network.LoginInterface
import com.example.dmap.Map.network.GetToiletByLocationResponse
import com.example.dmap.Map.network.ToiletInterface
import com.example.dmap.Network.createRetrofit
import retrofit2.Call


class ToiletRepository() {

    val SIGNUP_BASE_URL = "http://49.247.0.135:443"

    var user = com.example.dmap.User.User

    private val toiletInterface = createRetrofit(ToiletInterface::class.java, SIGNUP_BASE_URL)

    fun getNearReviewData(lat : Double , lon : Double) : Call<GetToiletByLocationResponse> {
        return toiletInterface.getToiletReviewByLocation(user.userToken!! ,lat, lon)
    }
}