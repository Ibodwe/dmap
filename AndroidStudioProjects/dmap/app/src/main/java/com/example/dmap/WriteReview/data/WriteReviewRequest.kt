package com.example.dmap.WriteReview.data

import java.util.*

data class WriteReviewRequest(
    val amount_of_tissue: Int,
    val clean_of_toilet: Int,
    val id: String ,
    val image: String? = null,
    val is_old: Int,
    val is_secret: Int,
    val latitude: Double,
    val longitude: Double,
    val sex: Int,
    val shot_detail: String,
    val title: String,
    val toiletId: String,
    val userId: String
)