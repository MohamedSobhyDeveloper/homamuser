package com.otex.homamuser.view.restaurantprofile

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityRestaurantProfileBinding
import com.otex.homamuser.utlitites.Constant
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.restaurantitem.model.Product
import com.otex.homamuser.view.restaurantprofile.`interface`.OnItemClick
import com.otex.homamuser.view.restaurantprofile.model.Menu
import com.otex.homamuser.view.restaurantprofile.adapter.BestDishesAdapter
import com.otex.homamuser.view.restaurantprofile.adapter.MenuResProfileAdapter
import com.squareup.picasso.Picasso

class RestaurantProfileActivity : BaseActivity(), OnItemClick {
    private var resturantProfileViewModel: ResturantProfileViewModel? = null
    lateinit var binding: ActivityRestaurantProfileBinding
    var phone:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        getRestaurant_details()

        click()
    }
    private fun click() {
        binding.backbtn.setOnClickListener {
            finish()
        }
        binding.imgPhone.setOnClickListener {
           dialPhoneNumber(phone)
        }

    }
    @SuppressLint("SetTextI18n")
    private fun initialize() {
        resturantProfileViewModel = ViewModelProvider(this).get(ResturantProfileViewModel::class.java)
        resturantProfileViewModel!!.restaurantProfilelivedata.observe(this) {

            if(it.data.logo.isNotEmpty()){
                Picasso.get().load(it.data.logo).into(binding.imgPastaprofile)
            }
            phone=it.data.phone
            binding.txtNameProfile.text=it.data.name
            binding.txtAddress.text=it.data.address+" "+it.data.district
            binding.txtDescription.text=it.data.description
            Picasso.get().load(intent.getStringExtra("image")).into(binding.imgCoverphoto)
            if(it.data.menus.isNotEmpty()) {
                setUpMenu(it.data.menus)
            }


        }
        resturantProfileViewModel!!.restaurantMenudata.observe(this) {

           // binding.txtDescription.text=it.data.products[0].description
            if(it.data.products.isEmpty()) {
                binding.txtNotFound.visibility=View.VISIBLE
            }else{
                binding.txtNotFound.visibility=View.GONE

            }
            val layoutManager = LinearLayoutManager(this)
            binding.recBestdishes.layoutManager = layoutManager
            val adapter =
                    BestDishesAdapter(this, it.data.products as ArrayList<Product>,intent.getStringExtra(Constant.restID)!!,intent.getStringExtra("isOpen").toString())
            binding.recBestdishes.adapter = adapter

        }

    }

    private fun setUpMenu(menus: List<Menu>) {
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recMenu.layoutManager = layoutManager
        val adapter =
                MenuResProfileAdapter(this,menus,this)
        binding.recMenu.adapter = adapter
        getMenuItem(intent.getStringExtra(Constant.restID)!!,menus[0].id.toString())

    }

    private fun getMenuItem(restId: String, menuId: String) {
        val map = HashMap<String, String?>()
        map.put("restId",restId)
        map.put("menuId",menuId)
        resturantProfileViewModel?.getRestaurantMenuItem(this,map)

    }

    @SuppressLint("NewApi")
    private fun getRestaurant_details() {

        if(intent.getStringExtra("isOpen").toString()=="1") {
           binding.txtClock.setTextColor(getColor(R.color.green))
            binding.txtClock.text = getString(R.string.open)

        }else{
            binding.txtClock.setTextColor(getColor(R.color.red))
            binding.txtClock.text = getString(R.string.close)

        }
        val map = HashMap<String, String?>()
        map.put(Constant.restID,intent.getStringExtra(Constant.restID))
        resturantProfileViewModel?.getRestaurantDetails(this,map)

    }


    override fun onClick(value: String?, name: String?) {
       getMenuItem(intent.getStringExtra(Constant.restID)!!, value!!)
        binding.best.text=name
    }
    fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }


}