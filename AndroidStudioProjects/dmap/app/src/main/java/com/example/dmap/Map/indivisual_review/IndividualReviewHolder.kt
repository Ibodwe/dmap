package com.example.dmap.Map.indivisual_review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dmap.R
import kotlinx.android.synthetic.main.individual_review_item.view.*

class IndividualReviewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(
    R.layout.individual_review_item , parent, false
)) {


    fun onBind(item : IndividualItem) {
        itemView.ratingBar.rating = 3.5f

    }



}