package com.otex.homamuser.view.myorder

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityMyOrderMapBinding
import com.otex.homamuser.utlitites.GPSTracker
import com.otex.homamuser.view.cart.CartActivity
import java.util.*

class MyOrderMapActivity : AppCompatActivity(), OnMapReadyCallback {
    private var myOrderViewModel: MyOrderViewModel? = null
    lateinit var binding: ActivityMyOrderMapBinding
    private var mapFragment: SupportMapFragment? = null
    private lateinit var mMap: GoogleMap
    private var gpsTracker: GPSTracker? = null
    private var currentLocation: LatLng? = null
    private lateinit var mylocation: LatLng
    private lateinit var streetStart: String
    private lateinit var geocode : Geocoder
    var addresses:List<Address>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyOrderMapBinding.inflate(layoutInflater)

        setContentView(binding.root)
        mapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment!!.getMapAsync(this)
        initialize()
        getprice()
        click()

    }

    private fun getprice() {

        myOrderViewModel?.getprice()

    }

    private fun click() {

        binding.backbtn.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        binding.conOrderNow.setOnClickListener {

            if (addresses != null && addresses!!.isNotEmpty()) {
                streetStart = addresses!![0].getAddressLine(0)//thoroughfare
                binding.txtAddress.text=streetStart
            }else{
                streetStart=""
            }
        }

    }

    private fun initialize() {

        mylocation= LatLng(0.0,0.0)
        geocode = Geocoder(this, Locale("ar"))//.getDefault())
        myOrderViewModel = ViewModelProvider(this).get(MyOrderViewModel::class.java)

    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        ActivityCompat.requestPermissions(
            this, arrayOf<String>(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            1
        )
        mMap = googleMap!!
        //  mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.uiSettings.isZoomGesturesEnabled=true
        gpsTracker = GPSTracker(this)
        if (gpsTracker!!.canGetLocation) {
//          binding.chooseLocationBtn.setEnabled(true);
            currentLocation = LatLng(gpsTracker!!.latitude, gpsTracker!!.longitude)
        }
        mMap.addMarker(
            this!!.currentLocation?.let {
                MarkerOptions().position(it)
                    .icon(bitmapDescriptorFromVector(this, R.drawable.baseline_room_black_18))
            })
        // mMap.addMarker(MarkerOptions().position(myLocation))//.title("Marker in Sydney"))
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(currentLocation
            , 10F))


        mMap.isMyLocationEnabled=true

        mMap.setOnMapClickListener { latLng ->

            mMap.clear()
            mylocation=latLng
            mMap.addMarker(MarkerOptions().position(mylocation)//.title("Marker in Sydney"))
//                        .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_pin_receive))
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation))
            addresses = geocode.getFromLocation(mylocation.latitude, mylocation.longitude, 1)


                streetStart = addresses!![0].getAddressLine(0)//thoroughfare
                binding.txtAddress.text=streetStart
        }
    }

    fun bitmapDescriptorFromVector(activity: Context, @DrawableRes vectorDrawableResourceId: Int): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(activity, vectorDrawableResourceId)
        vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
        val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

}


