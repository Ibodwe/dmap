package com.example.dmap.Data.source

import com.example.dmap.Network.SearchResponse
import retrofit2.Call

class KakaoMapRepository : KakaoMapSource {

    val kakaoRemoteData = com.example.dmap.Data.source.KakaoRemoteData()

    override suspend fun searchRoadByFoot(
        toiletName: String,
        toilet_latitude: String,
        toilet_longitude: String
    ): Call<SearchResponse> {
        return kakaoRemoteData.searchRoadByFoot(toiletName,toilet_latitude, toilet_longitude)
    }
}