package com.example.dmap.Map

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.dmap.Data.source.KakaoMapRepository
import com.example.dmap.Map.AddMap.AddMapActivity
import com.example.dmap.Map.Bottom_fragment.ToiletSemiInfo
import com.example.dmap.Map.CustomLocationData.DmapLocationData
import com.example.dmap.Map.Gps.GpsTracker
import com.example.dmap.R
import com.example.dmap.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*
import net.daum.mf.map.api.MapCircle
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapPoint.mapPointWithGeoCoord
import net.daum.mf.map.api.MapView

class MainActivity : AppCompatActivity(), MapView.POIItemEventListener, View.OnClickListener,
    MapView.CurrentLocationEventListener , MapView.MapViewEventListener{

    lateinit var binding: ActivityMainBinding

    var latitude: Double = 0.0
    var longitude: Double = 0.0

    var userCircle: MapCircle? = null

    lateinit var mDialog: Dialog

    val gpsTracker: GpsTracker by lazy { GpsTracker(this) }
    val viewModel: MainActivityViewModel by lazy { MainActivityViewModel(KakaoMapRepository()) }
    lateinit var mapView: MapView

    lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        showLoadingDialog()

        view.menuBtn.setOnClickListener(this)
        view.addToiletBtn.setOnClickListener(this)

        drawer = view.MenuDrawer

    }


    fun initMapView() {

        mapView = MapView(this)
        binding.mapView.addView(mapView)

        getLocation()
        mapView.zoomIn(true);
        mapView.zoomOut(true);
        binding.addToiletBtn.bringToFront()

        mapView.setMapViewEventListener(this)
        mapView.setPOIItemEventListener(this)
        mapView.setCurrentLocationEventListener(this)

    }

    fun getLocation() {
        gpsTracker.getLocation()

        if (gpsTracker.canGetLocation) {
            latitude = gpsTracker.latitude;
            longitude = gpsTracker.longitude;
        }

        mapView.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading

    }

    fun addMarker() {

        val marker = DmapLocationData("test22");

        marker.itemName = "첫번쨰 화장실"
        marker.tag = 0
        marker.mapPoint = mapPointWithGeoCoord(latitude, longitude)
        marker.markerType = MapPOIItem.MarkerType.BluePin;
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin;
        mapView.addPOIItem(marker)

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
        val temp = p1 as DmapLocationData
        ToiletSemiInfo().show(supportFragmentManager, "")

    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {

    }

    override fun onResume() {
        super.onResume()
        initMapView()
        addMarker()

    }

    override fun onDestroy() {
        super.onDestroy()
        gpsTracker.stopGps()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.menuBtn -> {
                if (drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.closeDrawer(Gravity.LEFT)
                } else {
                    drawer.openDrawer(Gravity.LEFT); }
            }

            R.id.addToiletBtn -> {
                binding.mapView.removeView(mapView)
                val intent = Intent(this , AddMapActivity::class.java)
                intent.putExtra("currentLongitude"  , longitude)
                intent.putExtra("currentLatitude" , latitude)
                startActivity(intent)
            }

        }
    }

    override fun onCurrentLocationUpdate(p0: MapView?, p1: MapPoint?, p2: Float) {

        if (p1 != null) {
            latitude = p1.mapPointGeoCoord?.latitude ?: 0.0
            longitude = p1.mapPointGeoCoord?.longitude ?: 0.0
        }

        Log.d("userCurrnetLocation", "currentLocation $latitude  $longitude")

    }

    override fun onCurrentLocationDeviceHeadingUpdate(p0: MapView?, p1: Float) {

    }

    override fun onCurrentLocationUpdateCancelled(p0: MapView?) {

    }

    override fun onCurrentLocationUpdateFailed(p0: MapView?) {

    }

    override fun onMapViewInitialized(p0: MapView?) {

    }

    override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {
        Log.d("mapViewCenterMove", "mavpie")

    }

    override fun onMapViewZoomLevelChanged(p0: MapView?, p1: Int) {
    }

    override fun onMapViewSingleTapped(p0: MapView?, p1: MapPoint?) {
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
        mDialog.dismiss()
    }

    fun showLoadingDialog(){
        mDialog= Dialog(this)
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.loading_fragment);
        mDialog.window?.setBackgroundDrawable(ColorDrawable (Color.TRANSPARENT));
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();
    }



}