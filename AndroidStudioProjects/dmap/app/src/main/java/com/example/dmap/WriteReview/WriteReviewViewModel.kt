package com.example.dmap.WriteReview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dmap.WriteReview.data.WriteReviewRepository
import com.example.dmap.WriteReview.data.WriteReviewRequest
import com.example.dmap.WriteReview.data.WriteReviewResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WriteReviewViewModel : ViewModel() {

    val repo = WriteReviewRepository()

    val _registerReviewMessage = MutableLiveData<Boolean>()
    val registerReviewMessage : LiveData<Boolean>
    get() = _registerReviewMessage


    fun registerReview(data : WriteReviewRequest){
        repo.registerReview(data).enqueue(object : Callback<WriteReviewResponse> {
            override fun onResponse(
                call: Call<WriteReviewResponse>,
                response: Response<WriteReviewResponse>
            ) {
                if(response.body()!= null && response.isSuccessful){
                    when(response.code()){
                        201 ->{ _registerReviewMessage.value = true }
                        401 ->{ _registerReviewMessage.value = false}
                    }
                }

            }

            override fun onFailure(call: Call<WriteReviewResponse>, t: Throwable) {
                _registerReviewMessage.value = false
            }
        })
    }
}