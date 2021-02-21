package com.example.dmap.Map.data

import com.example.dmap.Map.network.GetToiletByLocationResponse
import retrofit2.Call

interface ToiletDataSource {
    suspend fun getToiletByLcoation(lat : Double , lon : Double ) : Call<GetToiletByLocationResponse>
}
