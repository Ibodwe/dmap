package com.example.dmap.Map.AddMap

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dmap.Map.AddMap.data.AddMapRepo
import com.example.dmap.Map.AddMap.data.NewToiletRegisterRequest
import com.example.dmap.Map.AddMap.data.NewToiletRegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddMapViewModel : ViewModel() {

    val repo = AddMapRepo()

    val _registerResponse = MutableLiveData<Boolean>()
    val registerResponse : LiveData<Boolean>
    get() = _registerResponse

    fun registerNewToilet(data : NewToiletRegisterRequest){
        repo.registerNewToilet(data).enqueue(object : Callback<NewToiletRegisterResponse>{
            override fun onResponse(
                call: Call<NewToiletRegisterResponse>,
                response: Response<NewToiletRegisterResponse>
            ) {
                when(response.code()){
                    201 -> { _registerResponse.value = true}
                    401 -> { _registerResponse.value = false}
                }
            }

            override fun onFailure(call: Call<NewToiletRegisterResponse>, t: Throwable) {

            }
        })
    }
}