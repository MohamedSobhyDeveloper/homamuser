package com.otex.homamuser.view.cart

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityCartBinding
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.cart.adapter.CartAdapter
import com.otex.homamuser.view.myorder.MyOrderMapActivity
import com.squareup.picasso.Picasso
import com.tbruyelle.rxpermissions2.RxPermissions
import java.util.*


class CartActivity : BaseActivity(){
    private var cartViewModel : CartViewModel? = null
    lateinit var binding: ActivityCartBinding
    var total:String=""
    var restname:String=""
    var restId:String=""
    var restlogo:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkPermission()
        initialize()
        getcart()
        click()

    }

    @SuppressLint("CheckResult")
    private fun checkPermission() {
        val rxPermissions = RxPermissions(this)
        rxPermissions.setLogging(true)

        rxPermissions
                .request(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                )
                .subscribe { granted ->
                    if (granted) { // Always true pre-M
                        // I can control the camera now
                        Log.e("m", "permission")

                    } else {
                        Toast.makeText(this, "اسمح للتطبيق بالوصول إلى موقعك", Toast.LENGTH_SHORT).show()
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri: Uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)
                        // Oups permission denied
                    }
                }
    }





    private fun getcart() {

        cartViewModel?.getMyCart(this, null)

    }
    private fun click() {

        binding.backbtn.setOnClickListener {
            finish()
        }
        binding.conOrderNow.setOnClickListener {
            val intent = Intent(this, MyOrderMapActivity::class.java)
            intent.putExtra("total", total)
            intent.putExtra("restname", restname)
            intent.putExtra("restId", restId)
            intent.putExtra("restlogo", restlogo)

            startActivity(intent)
        }

    }

    private fun initialize() {
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        cartViewModel!!.mycartlivedata.observe(this) {

            if (it.data != null) {
                if (it.data.restaurant_logo.isNotEmpty()){
                    Picasso.get().load(it.data.restaurant_logo).into(binding.logo)
                }
                restlogo=it.data.restaurant_logo
                restId = it.data.restaurant_id
                binding.restName.text = it.data.restaurant
                binding.txtPriceTotalFirst.text = it.data.total.toString()
                binding.txtPriceTotalEnd.text = it.data.total.toString()
                binding.txtConNumorder.text = it.data.items.size.toString()
                binding.txtConTotal.text = it.data.total.toString()
                total = it.data.total.toString()
                restname = it.data.restaurant
                val layoutManager = LinearLayoutManager(this)
                binding.recOrderCart.layoutManager = layoutManager
                val adapter =
                    CartAdapter(this, it.data.items, object : CartAdapter.Clickvaluelistener {
                        override fun click(id: String) {
                            //  Toast.makeText(this@CartActivity, "backend prepare deleting", Toast.LENGTH_SHORT).show()
                            val map = HashMap<String, String?>()
                            map.put("id", id)
                            cartViewModel?.deleteCart(this@CartActivity, map)

                        }

                    })
                binding.recOrderCart.adapter = adapter


            }else{
                   binding.nestedScroll.visibility=View.GONE
                binding.conOrderNow.visibility=View.GONE
                binding.emptycart.visibility=View.VISIBLE

            }
        }

        cartViewModel!!.deleteItemlivedata.observe(this) {

            if(it.status=="1") {
                Toast.makeText(this@CartActivity, getString(R.string.itemdelete), Toast.LENGTH_SHORT).show()
                getcart()
            }

        }


    }

}