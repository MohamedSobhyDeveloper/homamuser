package com.otex.homamuser.view.restaurantitem

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.databinding.ActivityRestaurantItemBinding
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.cart.CartActivity
import com.otex.homamuser.view.restaurantprofile.RestaurantProfileActivity
import com.otex.homamuser.view.restaurantprofile.ResturantProfileViewModel
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.AdditionsAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.ChooseSizeAdapter

class RestaurantItemActivity : BaseActivity() {
    private var resturantMenuViewModel: ResturantProfileViewModel? = null
    lateinit var binding: ActivityRestaurantItemBinding
    var num:Int=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        click()
        getrestaurant()
    }
    private fun click() {
        binding.backbtn.setOnClickListener {
            startActivity(Intent(this, RestaurantProfileActivity::class.java))
            finish()
        }
        binding.addToCart.setOnClickListener {
            startActivity(Intent(this,CartActivity::class.java))
            finish()
        }
        binding.add.setOnClickListener {
            binding.numOrder.setText((num++).toString())
            binding.txtSalary.setText((120*num).toString()+"L.E")
        }
        binding.minues.setOnClickListener {
            binding.numOrder.setText((num--).toString())
            binding.txtSalary.setText((120*num).toString()+"L.E")
        }

    }
    private fun initialize() {
        resturantMenuViewModel = ViewModelProvider(this).get(ResturantProfileViewModel::class.java)
        resturantMenuViewModel!!.restaurantMenudata.observe(this) {

            binding.foodName.text=it.data.products[0].name
            binding.description.text=it.data.products[0].description

            val layoutManager = LinearLayoutManager(this)
            binding.recyclerSize.layoutManager = layoutManager
            val adapter =
                    ChooseSizeAdapter(this,it.data.products[0].options)
            binding.recyclerSize.adapter = adapter

        }
        resturantMenuViewModel!!.restaurantMenudata.observe(this) {

            val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            binding.recyclerAddition.layoutManager = layoutManager
            val adapter =
                AdditionsAdapter(this, it.data.products[0].additions)
            binding.recyclerAddition.adapter = adapter

        }

    }

    private fun getrestaurant() {

        resturantMenuViewModel?.getRestaurantMenu(this)

    }



}