package com.example.dmap.ToiletReviewDialog.indivisual_review

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dmap.R
import com.example.dmap.WriteReview.WriteReviewActivity
import kotlinx.android.synthetic.main.individual_review_item.view.*

class IndividualReviewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(
    R.layout.individual_review_item , parent, false
)) {



    fun onBind(item : IndividualItem) {
        itemView.ratingBar.rating = 3.5f
        itemView.reviewWriterId.text = item.title
        itemView.ReviewContextTv.text = item.shot_detail

        itemView.setOnClickListener {
            //val intent = Intent(itemView.context, WriteReviewActivity::class.java)
           // intent.putExtra("data" , item)
           // itemView.context.startActivity(intent)

            Toast.makeText(itemView.context,"click " , Toast.LENGTH_SHORT).show()
        }
    }



}