package com.otex.homamuser.view.restaurantprofile

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.databinding.ActivityRestaurantProfileBinding
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.restaurantprofile.`interface`.OnItemClick
import com.otex.homamuser.view.specialorder.SpecialOrdesActivity
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.BestDishesAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.MenuResProfileAdapter

class RestaurantProfileActivity : BaseActivity(), OnItemClick {
    private var resturantProfileViewModel: ResturantProfileViewModel? = null
    lateinit var binding: ActivityRestaurantProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getRestaurant_details()
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
    private fun initialize() {
        resturantProfileViewModel = ViewModelProvider(this).get(ResturantProfileViewModel::class.java)
        resturantProfileViewModel!!.restaurantProfilelivedata.observe(this) {

            binding.txtNameProfile.text=it.data.name
            binding.txtAddress.text=it.data.address+" "+it.data.district

            val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            binding.recMenu.layoutManager = layoutManager
            val adapter =
                MenuResProfileAdapter(this,it.data.menus,this)
            binding.recMenu.adapter = adapter

        }
        resturantProfileViewModel!!.restaurantProfilelivedata.observe(this) {

            val layoutManager = LinearLayoutManager(this)
            binding.recBestdishes.layoutManager = layoutManager
            val adapter =
                BestDishesAdapter(this, null)
            binding.recBestdishes.adapter = adapter

        }

    }

    private fun getRestaurant_details() {

        resturantProfileViewModel?.getItemMenu(this)

    }

    override fun onClick(value: String?) {
        val layoutManager = LinearLayoutManager(this)
        binding.recBestdishes.layoutManager = layoutManager
        val adapter =
                BestDishesAdapter(this, null)
        binding.recBestdishes.adapter = adapter
    }


}