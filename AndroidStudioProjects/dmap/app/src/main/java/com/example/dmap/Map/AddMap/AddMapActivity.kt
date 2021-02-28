package com.example.dmap.Map.AddMap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.dmap.Map.AddMap.data.NewToiletRegisterRequest
import com.example.dmap.Map.CustomLocationData.DmapLocationData
import com.example.dmap.Map.network.ToiletData
import com.example.dmap.R
import com.example.dmap.User.User
import com.example.dmap.databinding.ActivityAddMapBinding
import kotlinx.android.synthetic.main.toilet_bottom_info.*
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapReverseGeoCoder
import net.daum.mf.map.api.MapView
import java.util.*
import kotlin.collections.ArrayList


lateinit var mapView: MapView
lateinit var binding: ActivityAddMapBinding

var latitude: Double = 0.0
var longitude: Double = 0.0
lateinit var geoCoder: MapReverseGeoCoder
var marker: MapPOIItem? = null

var address  = ArrayList<String>()

val viewModel : AddMapViewModel by lazy {
    AddMapViewModel()
}

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
        Observe()




        initMapView()

    }


    fun initMapView() {
        mapView = MapView(this)
        binding.addMapView.addView(mapView)
        mapView.zoomIn(true);
        mapView.zoomOut(true);
        mapView.setZoomLevel(0, false)
        mapView.setMapViewEventListener(this)

        mapView.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving

        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);
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

        marker = MapPOIItem();

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

        latitude = p1?.mapPointGeoCoord?.latitude ?: 0.0
        longitude = p1?.mapPointGeoCoord?.longitude ?: 0.0

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

                if(binding.toiletAddress.text.isEmpty()){
                    Toast.makeText(this , "입력할 화장실을 지도상에서 터치 해주세요.", Toast.LENGTH_SHORT).show()
                }else if (binding.toiletDetailAddress.text.isEmpty()){
                    Toast.makeText(this , "상세 주소를 입력해주세요.", Toast.LENGTH_SHORT).show()
                }else if (binding.toiletName.text.isEmpty()){
                    Toast.makeText(this , "화장실 이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
                }else{

                    getAddress(binding.toiletAddress.text.toString())
                    viewModel.registerNewToilet(NewToiletRegisterRequest(
                        city_name = address.get(0),
                        detail = null,
                        dong_name = address.get(2),
                        goo_name = address.get(1),
                        id = User.userId.toString() + System.currentTimeMillis().toString(),
                        latitude = latitude ,
                        longitude =  longitude,
                        name = binding.toiletName.text.toString(),
                        sex = 0
                    ))

                }
            }
        }
    }

    fun getAddress(data : String){
        val dataDummy = data.split(" ")

        dataDummy.forEach {
            address.add(it)
        }
    }

    fun Observe(){
        viewModel.registerResponse.observe(this , androidx.lifecycle.Observer {
            if(it) {
                Toast.makeText(this , "등록에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                binding.addMapView.removeView(mapView)
                finish()
            }else{
                Toast.makeText(this , "등록에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}