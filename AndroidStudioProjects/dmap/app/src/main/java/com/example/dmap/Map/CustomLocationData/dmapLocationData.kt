package com.example.dmap.Map.CustomLocationData

import com.example.dmap.Map.network.ToiletData
import net.daum.mf.map.api.MapPOIItem

data class DmapLocationData constructor(val data : ToiletData) : MapPOIItem(){

}