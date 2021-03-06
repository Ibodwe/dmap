package com.example.dmap.ToiletReviewDialog.data

data class ToiletReviewResponse(
    val result: List<Result>
)

data class Result(
    val amount_of_tissue: Int,
    val clean_of_toilet: Int,
    val createdAt: String,
    val id: String,
    val image: String?,
    val is_old: Int,
    val is_secret: Int,
    val latitude: Double,
    val longitude: Double,
    val sex: Int,
    val shot_detail: String,
    val title: String,
    val toiletId: String,
    val updatedAt: String,
    val userId: String,
)