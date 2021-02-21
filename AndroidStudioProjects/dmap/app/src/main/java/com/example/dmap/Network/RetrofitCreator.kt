package com.example.dmap.Network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//retrofit 을 생성하는 공통 모듈
fun <T> createRetrofit(cls : Class<T> , baseUrl : String) : T {

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
       // .addConverterFactory(ToStringConverterFactory())
       // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(createOkHttpClient())
        .build()
        .create(cls)
}


private fun createOkHttpClient() : OkHttpClient{

    val builder : OkHttpClient.Builder = OkHttpClient.Builder()

    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    builder.addInterceptor(interceptor)
    return builder.build()
}