package com.example.dmap.Map.AddMap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.dmap.Map.CustomLocationData.DmapLocationData
import com.example.dmap.R
import com.example.dmap.databinding.ActivityAddMapBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapReverseGeoCoder
import net.daum.mf.map.api.MapView


lateinit var mapView: MapView
lateinit var binding: ActivityAddMapBinding

var latitude: Double = 0.0
var longitude: Double = 0.0


lateinit var geoCoder: MapReverseGeoCoder

var marker: DmapLocationData? = null

class AddMapActivity : AppCompatActivity(), MapView.MapViewEventListener,
    MapReverseGeoCoder.ReverseGeoCodingResultListener, View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityAddMapBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.registerBtn.setOnClickListener(this)
        longitude = intent.getDoubleExtra("currentLongitude", longitude)
        latitude = intent.getDoubleExtra("currentLatitude", latitude)

        initMapView()

    }


    fun initMapView() {
        mapView = MapView(this)
        binding.addMapView.addView(mapView)
        mapView.zoomIn(true);
        mapView.zoomOut(true);
        mapView.setZoomLevel(0, false)
        mapView.setMapViewEventListener(this)

    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding.addMapView.removeView(mapView)
    }

    override fun onMapViewInitialized(p0: MapView?) {

    }

    override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {

    }

    override fun onMapViewZoomLevelChanged(p0: MapView?, p1: Int) {

    }

    override fun onMapViewSingleTapped(p0: MapView?, p1: MapPoint?) {
        Log.d("singleTouch", p1?.mapPointGeoCoord?.latitude.toString())
        geoCoder = MapReverseGeoCoder("6bfd1f6a1281ce6c1b3e1a12ac7525e1", p1, this, this)
        geoCoder.startFindingAddress()


        if (marker != null) { mapView.removePOIItem(marker) }

        //marker = DmapLocationData("test22");

        marker?.apply {
            itemName = "새로 등록할 화장실"
            tag = 0
            mapPoint = MapPoint.mapPointWithGeoCoord(
                p1?.mapPointGeoCoord?.latitude ?: 0.0,
                p1?.mapPointGeoCoord?.longitude ?: 0.0
            )
            markerType = MapPOIItem.MarkerType.BluePin;
            selectedMarkerType = MapPOIItem.MarkerType.RedPin;
            mapView.addPOIItem(marker)
        }

    }

    override fun onMapViewDoubleTapped(p0: MapView?, p1: MapPoint?) {

    }

    override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {

    }

    override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {

    }

    override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {

    }

    override fun onMapViewMoveFinished(p0: MapView?, p1: MapPoint?) {

    }

    override fun onReverseGeoCoderFailedToFindAddress(p0: MapReverseGeoCoder?) {
        Log.d("address222", p0.toString())
    }

    override fun onReverseGeoCoderFoundAddress(p0: MapReverseGeoCoder?, p1: String?) {
        // 확인 dialog
        binding.toiletAddress.text = p1

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.registerBtn -> {
                binding.addMapView.removeView(mapView)
                finish()
            }
        }


    }
}