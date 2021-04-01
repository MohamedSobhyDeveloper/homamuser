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
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivitySelectAddressBinding
import com.otex.homamuser.utlitites.GPSTracker
import com.otex.homamuser.view.baseActivity.BaseActivity
import java.io.IOException
import java.util.*

class SelectAddressActivity : BaseActivity(), OnMapReadyCallback {
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
            Places.initialize(this.applicationContext, "AIzaSyAQpYyD4auxymJQthsGsQoKtm6lcYTT0kU")
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
                            .draggable(true)
                })
        // mMap.addMarker(MarkerOptions().position(myLocation))//.title("Marker in Sydney"))
        mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(currentLocation
                        , 10F))


        mMap.isMyLocationEnabled=true

//        mMap.setOnMapClickListener { latLng ->
//
//            mMap.clear()
//            mylocation=latLng
//            mMap.addMarker(MarkerOptions().position(mylocation)//.title("Marker in Sydney"))
////                        .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_pin_receive))
//            )
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation))
//            addresses = geocode.getFromLocation(mylocation.latitude, mylocation.longitude, 1)
//
//
//                streetStart = addresses!![0].getAddressLine(0)//thoroughfare
//                binding.txtAddress.setText(streetStart)
//
//
//        }


        mMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragStart(arg0: Marker) {
                // TODO Auto-generated method stub
                Log.d("System out", "onMarkerDragStart..." + arg0.position.latitude.toString() + "..." + arg0.position.longitude)
            }

            override fun onMarkerDragEnd(arg0: Marker) {
                // TODO Auto-generated method stub
                Log.d("System out", "onMarkerDragEnd..." + arg0.position.latitude.toString() + "..." + arg0.position.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLng(arg0.position))
            }

            override fun onMarkerDrag(arg0: Marker?) {
                // TODO Auto-generated method stub
                Log.i("System out", "onMarkerDrag...")
            }
        })
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
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val place = Autocomplete.getPlaceFromIntent(data!!)
                mylocation = place.latLng!!
                if (mylocation != null) {
                   val lat: Double = mylocation.latitude
                    val lng :Double = mylocation.longitude
                    val geocoder: Geocoder
                    val addresses: List<Address>
                    geocoder = Geocoder(this, Locale("ar"))
                    try {
                        addresses = geocoder.getFromLocation(lat, lng, 1)
                        if (addresses.size > 0) {
                            val city = addresses[0].locality
                            val state = addresses[0].adminArea
                            val subLocality = addresses[0].subLocality
                            val country = addresses[0].countryName
                            val postalCode = addresses[0].postalCode
                            val knownName = addresses[0].featureName
                            streetStart = if (place.address != null && place.address!!.contains(",")) {
                                val addressAr = place.address!!.split(",".toRegex()).toTypedArray()
                                "$city, $state" + ", " + country
                            } else {
                                "$subLocality, $state" + ", " + country
                            }
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    if (streetStart == null) streetStart = place.address.toString()
                    if (streetStart != null && streetStart!!.contains("null,")) {
                        streetStart = streetStart!!.replace("null,", "")
                        streetStart = streetStart!!.replace("null", "")
                    }
                    binding.txtAddress.text = streetStart + ""
                                       mMap.clear()
                    mMap!!.addMarker(MarkerOptions().position(mylocation).title(place.name + ""))
                            .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.location))
                    mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 10f))

                }
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                val status = Autocomplete.getStatusFromIntent(data!!)
            } else if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }

}