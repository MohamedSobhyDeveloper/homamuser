package com.otex.homamuser.view.restaurantprofile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.databinding.ActivityRestaurantProfileBinding
import com.otex.homamuser.utlitites.Constant
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.restaurantitem.model.Product
import com.otex.homamuser.view.restaurantprofile.`interface`.OnItemClick
import com.otex.homamuser.view.restaurantprofile.model.Menu
import com.otex.homamuser.view.specialorder.SpecialOrdesActivity
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.BestDishesAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.MenuResProfileAdapter
import com.squareup.picasso.Picasso

class RestaurantProfileActivity : BaseActivity(), OnItemClick {
    private var resturantProfileViewModel: ResturantProfileViewModel? = null
    lateinit var binding: ActivityRestaurantProfileBinding

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
            startActivity(Intent(this, SpecialOrdesActivity::class.java))
            finish()
        }

    }
    @SuppressLint("SetTextI18n")
    private fun initialize() {
        resturantProfileViewModel = ViewModelProvider(this).get(ResturantProfileViewModel::class.java)
        resturantProfileViewModel!!.restaurantProfilelivedata.observe(this) {

            binding.txtNameProfile.text=it.data.name
            binding.txtAddress.text=it.data.address+" "+it.data.district
            Picasso.get().load(intent.getStringExtra("image")).into(binding.imgCoverphoto)
            if(it.data.menus.isNotEmpty()) {
                setUpMenu(it.data.menus)
            }


        }
        resturantProfileViewModel!!.restaurantMenudata.observe(this) {

            val layoutManager = LinearLayoutManager(this)
            binding.recBestdishes.layoutManager = layoutManager
            val adapter =
                    BestDishesAdapter(this, it.data.products as ArrayList<Product>,intent.getStringExtra(Constant.restID)!!)
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

    private fun getRestaurant_details() {
        val map = HashMap<String, String?>()
        map.put(Constant.restID,intent.getStringExtra(Constant.restID))
        resturantProfileViewModel?.getRestaurantDetails(this,map)

    }


    override fun onClick(value: String?) {
       getMenuItem(intent.getStringExtra(Constant.restID)!!, value!!)
    }


}