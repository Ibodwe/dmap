package com.example.dmap.Map.AddMap.data

import com.example.dmap.Map.AddMap.network.NewToilet
import com.example.dmap.Network.createRetrofit
import com.example.dmap.User.User
import retrofit2.Call
import javax.security.auth.callback.Callback

class AddMapRepo {

    val retrofit = createRetrofit(NewToilet::class.java, "http://49.247.0.135:443/")


    fun registerNewToilet(data : NewToiletRegisterRequest) : Call<NewToiletRegisterResponse> {

       return retrofit.newToilet(User.userToken!! , data)
    }
}