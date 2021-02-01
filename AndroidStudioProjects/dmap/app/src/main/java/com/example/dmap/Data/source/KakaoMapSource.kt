package com.example.dmap.Data.source


import com.example.dmap.Network.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KakaoMapSource {



    @GET("{toilet_name},{toilet_latitude},{toilet_longitude}")
    suspend fun searchRoadByFoot(@Path (value ="toilet_name" ,encoded = true) toiletName : String
                                 , @Path(value = "toilet_latitude" , encoded = true) toilet_latitude : String ,
                                    @Path(value = "toilet_longitude" ,encoded = true) toilet_longitude : String) : retrofit2.Call<SearchResponse>
}