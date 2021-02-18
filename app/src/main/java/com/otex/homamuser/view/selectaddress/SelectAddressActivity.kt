package com.otex.homamuser.view.selectaddress

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
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
import com.google.android.gms.location.places.ui.PlacePicker.getLatLngBounds
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivitySelectAddressBinding
import com.otex.homamuser.utlitites.GPSTracker
import java.util.*

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

        initialize()
        click()

    }
    private fun click() {


        binding.editaddress.setOnClickListener {
            Places.initialize(this.applicationContext, "AIzaSyA_IUA7wNx4fsKZP0NE3KhxDtEI3XVu8sA")
            val fields = Arrays.asList(Place.Field.LAT_LNG, Place.Field.ADDRESS, Place.Field.NAME)
            var intent: Intent
            if (currentLocation != null) {
                val latLngBounds = getLatLngBounds(currentLocation!!)
                intent = Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .setLocationRestriction(RectangularBounds.newInstance(
                                latLngBounds))
                        .build((this)!!)
            } else {
                intent = Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .build((this)!!)
           }
            startActivityForResult(intent, 1)
        }

        binding.btnSelect.setOnClickListener {

            val returnIntent = Intent()
             returnIntent.putExtra("result", streetStart)
            returnIntent.putExtra("latitude", mylocation.latitude.toString())
            returnIntent.putExtra("longitude", mylocation.longitude.toString())
            setResult(Activity.RESULT_OK,returnIntent)
             finish()
        }

    }
    fun getLatLngBounds(center: LatLng): LatLngBounds {
        val radiusDegrees = 5.0
        val northEast = LatLng(center.latitude + radiusDegrees, center.longitude + radiusDegrees)
        val southWest = LatLng(center.latitude - radiusDegrees, center.longitude - radiusDegrees)
        return LatLngBounds.builder().include(northEast).include(southWest).build()
    }
    private fun initialize() {
        mapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment!!.getMapAsync(this)
        mylocation= LatLng(0.0,0.0)
        geocode = Geocoder(this, Locale("ar"))//.getDefault())

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
                this.currentLocation?.let {
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
                binding.txtAddress.setText(streetStart)


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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }
}