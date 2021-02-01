package com.example.dmap.Map.indivisual_review

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class IndividualReviewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() , IndividualRecyclerModel {

    private val list = mutableListOf<IndividualItem?>()

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        list[position]?.let { (holder as? IndividualReviewHolder)?.onBind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return IndividualReviewHolder(parent)
    }

    override fun addItem(imageData: IndividualItem?) {
        list.add(imageData)
        notifyDataSetChange()
    }

    override fun getItem(position: Int): IndividualItem? {
        return list[position]
    }

    override fun notifyDataSetChange() {
    }

    override lateinit var onClick: (Int) -> Unit

}