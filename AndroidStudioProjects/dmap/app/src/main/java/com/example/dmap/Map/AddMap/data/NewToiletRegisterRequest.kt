package com.example.dmap.Map.AddMap.data
data class NewToiletRegisterRequest(
    val city_name: String,
    val detail: String?,
    val dong_name: String?,
    val goo_name: String?,
    val id: String,
    val image: String?=null,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val open_time: String? = null,
    val sex: Int,
    val street_name: String? = null,
    val street_num_main: String? = null,
    val street_num_sub: String? = null
)