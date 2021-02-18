package com.otex.homamuser.view.selectaddress

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityMyOrderMapBinding
import com.otex.homamuser.databinding.ActivitySelectAddressBinding
import com.otex.homamuser.utlitites.GPSTracker
import com.otex.homamuser.view.cart.CartActivity
import com.otex.homamuser.view.myorder.MyOrderViewModel

class SelectAddressActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var binding: ActivitySelectAddressBinding
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
        binding = ActivitySelectAddressBinding.inflate(layoutInflater)

        setContentView(binding.root)
        mapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment!!.getMapAsync(this)
        click()

    }
    private fun click() {

//        binding.backbtn.setOnClickListener {
//            val intent = Intent(this, CartActivity::class.java)
//            startActivity(intent)
//        }
//
//        binding.conOrderNow.setOnClickListener {
//
//            if (addresses != null && addresses!!.isNotEmpty()) {
//                streetStart = addresses!![0].getAddressLine(0)//thoroughfare
//                binding.txtAddress.setText(streetStart);
//            }else{
//                streetStart=""
//            }
//        }

    }

    override fun onMapReady(googleMap: GoogleMap?) {

    }

}