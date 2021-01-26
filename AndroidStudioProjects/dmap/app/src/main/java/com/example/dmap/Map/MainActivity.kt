package com.example.dmap.Map

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.dmap.Bottom_fragment.ToiletSemiInfo
import com.example.dmap.Map.CustomLocationData.dmapLocationData
import com.example.dmap.Map.Gps.GpsTracker
import com.example.dmap.databinding.ActivityMainBinding
import net.daum.android.map.coord.MapCoord
import net.daum.mf.map.api.MapCircle
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapPoint.mapPointWithGeoCoord
import net.daum.mf.map.api.MapView

class MainActivity : AppCompatActivity() , MapView.POIItemEventListener {

    lateinit var binding: ActivityMainBinding

     var latitude : Double =0.0
     var longitude : Double =0.0

     var userCircle : MapCircle? =null

     val gpsTracker : GpsTracker by lazy {
         GpsTracker(this)
     }

    lateinit var mapView : MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)

        initMapView()

        addMarker()

        mapView.setPOIItemEventListener(this);

        showCurrentUserLocation()

    }

    fun initMapView(){
        mapView = MapView(this)
        binding.mapView.addView(mapView)
        getLocation()

        mapView.zoomIn(true);
        mapView.zoomOut(true);

    }

    fun getLocation(){
        gpsTracker.getLocation()

        if(gpsTracker.canGetLocation){
            latitude = gpsTracker.latitude;
            longitude = gpsTracker.longitude;
        }

        Log.d("location" , "latitude $latitude  longitude $longitude")

        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(latitude,longitude), 3, true);
    }

    fun addMarker(){
        val marker = dmapLocationData("test22");

        marker.itemName = "첫번쨰 화장실"
        marker.tag =0
        marker.mapPoint = mapPointWithGeoCoord(latitude,longitude)
        marker.markerType = MapPOIItem.MarkerType.BluePin;
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin;

        mapView.addPOIItem(marker);
    }

    fun showCurrentUserLocation(){
        if(userCircle!=null){
            mapView.removeCircle(userCircle)
        }

        userCircle = MapCircle(MapPoint.mapPointWithGeoCoord(latitude+0.01,longitude+0.01)
            ,3 , Color.rgb(255,0,0) , Color.rgb(255,0,0))
        mapView.addCircle(userCircle)

    }

    override fun onCalloutBalloonOfPOIItemTouched(
        p0: MapView?,
        p1: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?
    ) {

    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {

    }

    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
         val temp = p1 as dmapLocationData
        ToiletSemiInfo().show(supportFragmentManager,"")

    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        gpsTracker.stopGps()
    }
}