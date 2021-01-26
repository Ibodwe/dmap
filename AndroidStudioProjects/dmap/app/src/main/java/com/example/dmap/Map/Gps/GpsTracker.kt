package com.example.dmap.Map.Gps

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import java.lang.Exception

class GpsTracker constructor(val context : Context) : Service(), LocationListener {

    var isGpsEnable = false

    var isNetworkEnabled = false

    var canGetLocation =false

    //lateinit var location : Location
    private lateinit var locationManager: LocationManager

    var latitude = 0.0
    var longitude = 0.0

    private  var currentLocation : Location? = null
    //일정 거리마다 업데이트
    val MIN_DISTANCE_CHANGE_FOR_UPDATES : Float = 10f

    // 일정 시간마다 업데이트
    val MIN_TIME_BW_UPDATES : Long = 1000*60*1

    var request  = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,  android.Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onBind(intent: Intent?): IBinder? {

        return null
    }

    override fun onLocationChanged(location: Location) {
        Log.d("locationChagne" , location.latitude.toString())
    }

    fun getLocation() : Location?{

        try{
            locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager

            isGpsEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            //net work gps 둘 다 안됨
            if(!isGpsEnable && ! isNetworkEnabled){

            }else{

                canGetLocation = true
                // network으로 위치 가져오기
                if(isNetworkEnabled){

                    //network가 안된다면 --> 요청
                    if(ActivityCompat.checkSelfPermission(context , android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(context , android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(context as Activity ,  request, 101)
                    }
                }

                //location 업데이트 요청
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                MIN_TIME_BW_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES, this)

                currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                if(currentLocation!=null){
                    latitude = currentLocation?.latitude ?: 0.0
                    longitude = currentLocation?.longitude ?: 0.0
                }

                //휴대폰의 gps로 위치 가져오기

                if(isGpsEnable){
                    if(currentLocation ==null){

                        if(ActivityCompat.checkSelfPermission(context , android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(context , android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(context as Activity ,  request, 101)
                        }

                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this)

                        currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                        if(currentLocation!=null){
                            latitude = currentLocation?.latitude ?: 0.0
                            longitude = currentLocation?.longitude ?: 0.0
                        }

                    }
                }


            }

        }catch (e : Exception){
            e.printStackTrace()
        }

        return currentLocation
    }

    fun stopGps(){
        if(currentLocation!=null)
            locationManager.removeUpdates(this)
    }

    fun canGetLocation() : Boolean{

        return canGetLocation
    }


}