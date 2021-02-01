package com.example.dmap.Map.indivisual_review

interface IndividualRecyclerModel {

    fun addItem(imageData: IndividualItem?)

    fun getItem(position: Int): IndividualItem?

    fun getItemCount(): Int

    fun notifyDataSetChange()

    var onClick: (Int) -> Unit
}
