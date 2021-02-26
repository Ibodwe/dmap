package com.example.dmap.Map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmap.Data.source.KakaoMapRepository
import com.example.dmap.Map.data.ToiletRepository
import com.example.dmap.Map.network.GetToiletByLocationResponse
import com.example.dmap.Map.network.ToiletData
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel () : ViewModel() {

    var repo = ToiletRepository()

    private var _review_data_list = MutableLiveData<List<ToiletData>>()
    val review_data_list : LiveData<List<ToiletData>>
    get() = _review_data_list

    fun getToiletReviewByLocation(current_lat : Double, current_lon : Double) {
        viewModelScope.launch {
            repo.getNearReviewData(current_lat, current_lon).enqueue(object : Callback<GetToiletByLocationResponse>{
                override fun onResponse(
                    call: Call<GetToiletByLocationResponse>,
                    response: Response<GetToiletByLocationResponse>
                ) {

                    if(response.isSuccessful && response.body()!= null){
                        _review_data_list.value = response.body()!!.data.toiletData
                    }

                }

                override fun onFailure(call: Call<GetToiletByLocationResponse>, t: Throwable) {

                }
            })
        }
    }



}