package com.otex.homamuser.view.restaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityHomeBinding
import com.otex.homamuser.databinding.ActivityResturantBinding
import com.otex.homamuser.view.home.HomeActivityViewModel
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.CountrySpecialAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.FoodLoveAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.SpecialOrderAdapter

class ResturantActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResturantBinding

    private var homeActivityViewModel : HomeActivityViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResturantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        getrestaurant()

    }

    private fun getrestaurant() {

        homeActivityViewModel?.getRestaurant()

    }
    private fun initialize() {

        homeActivityViewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)

        homeActivityViewModel!!.restaurantLiveData.observe(this) {

            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.recCountry.layoutManager = layoutManager
            val adapter =
                    CountrySpecialAdapter(this,null)
            binding.recCountry.adapter = adapter

        }
        homeActivityViewModel!!.countryhomelivedata.observe(this) {

            val layoutManager = LinearLayoutManager(this)
            binding.recRestaurants.layoutManager = layoutManager
            val adapter =
                    SpecialOrderAdapter(this,null)
            binding.recRestaurants.adapter = adapter

        }

    }
}