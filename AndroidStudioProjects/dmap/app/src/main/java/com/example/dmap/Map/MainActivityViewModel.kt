package com.example.dmap.Map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmap.Data.source.KakaoMapRepository
import kotlinx.coroutines.launch

class MainActivityViewModel (val repository: KakaoMapRepository ) : ViewModel() {


    fun getRoadDataByFoot(toiletName : String  , toilet_latitude : String, toilet_longitude : String) {
        viewModelScope.launch {
            repository.kakaoRemoteData.searchRoadByFoot(toiletName, toilet_latitude, toilet_longitude)
        }



    }

}