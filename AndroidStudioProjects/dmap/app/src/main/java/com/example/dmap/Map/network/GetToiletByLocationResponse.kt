package com.example.dmap.Map.network

data class GetToiletByLocationResponse(
    val `data`: Data
)

data class Data(
    val toiletData: List<ToiletData>
)

data class ToiletData(
    val city_name: String,
    val createdAt: String,
    val detail: String,
    val dong_name: String,
    val goo_name: String,
    val id: String,
    val image: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val open_time: String,
    val sex: Int,
    val street_name: String,
    val street_num_main: String,
    val street_num_sub: String,
    val updatedAt: String
)