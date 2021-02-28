package com.example.dmap.ToiletReviewDialog

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.dmap.ToiletReviewDialog.indivisual_review.IndividualRecyclerModel
import com.example.dmap.ToiletReviewDialog.data.ToiletReviewResponse
import com.example.dmap.ToiletReviewDialog.indivisual_review.IndividualItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ToiletSemiInfoViewModel(val imageRecyclerView: IndividualRecyclerModel) : ViewModel() {

    val repo = ToiletSemiInfoRepository()

    fun loadMoreData (toiletId : String){
        repo.getToiletReviewById(toiletId).enqueue(object : Callback<ToiletReviewResponse> {

            override fun onResponse(
                call: Call<ToiletReviewResponse>,
                response: Response<ToiletReviewResponse>
            ) {
                if(response.body()!=null && response.isSuccessful){

                    response.body()!!.result.forEach {

                        imageRecyclerView.addItem(
                            IndividualItem(
                                it.amount_of_tissue,
                                it.clean_of_toilet,
                                it.createdAt,
                                it.id,
                                it.image,
                                it.is_old,
                                it.is_secret,
                                it.latitude,
                                it.longitude,
                                it.sex,
                                it.shot_detail,
                                it.title,
                                it.toiletId,
                                it.updatedAt,
                                it.userId
                            )
                        )

                    }
                    imageRecyclerView.notifyDataSetChange()
                }
            }

            override fun onFailure(call: Call<ToiletReviewResponse>, t: Throwable) {

            }
        })
    }

}