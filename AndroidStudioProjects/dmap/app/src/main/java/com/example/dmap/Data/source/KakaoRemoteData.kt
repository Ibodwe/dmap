package com.example.dmap.Data.source

import com.example.dmap.Network.SearchResponse
import com.example.dmap.Network.createRetrofit
import retrofit2.Call

class KakaoRemoteData : KakaoMapSource {

    companion object{
        val KakaoUrl : String = "https://map.kakao.com/link/to/"
    }

    private val kakaoServiceInterface = createRetrofit(KakaoMapSource::class.java, KakaoUrl)


    override suspend fun searchRoadByFoot(
        toiletName: String,
        toilet_latitude: String,
        toilet_longitude: String
    ): Call<SearchResponse> {
        return kakaoServiceInterface.searchRoadByFoot(toiletName, toilet_latitude , toilet_longitude)
    }
}