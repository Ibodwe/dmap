package com.example.dmap

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this , "6bfd1f6a1281ce6c1b3e1a12ac7525e1")

    }
}