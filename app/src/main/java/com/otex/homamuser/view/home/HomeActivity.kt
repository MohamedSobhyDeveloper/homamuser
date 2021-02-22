package com.otex.homamuser.view.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityHomeBinding
import com.otex.homamuser.view.aboutus.AboutUsActivity
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.cart.CartActivity
import com.otex.homamuser.view.contactus.ContactUsActivity
import com.otex.homamuser.view.myprofile.MyProfileActivity
import com.otex.homamuser.view.restaurant.ResturantActivity
import com.otex.homamuser.view.specialorder.SpecialOrdesActivity
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.RestaurantAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.SpecialOrderAdapter
import java.util.*


class HomeActivity : BaseActivity() {

    private lateinit var binding : ActivityHomeBinding

    private var homeActivityViewModel : HomeActivityViewModel? = null

    var timer: Timer? = null
    var recommendCount = 0
    var restaurantAdapter: RestaurantAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()

        setuptoolbar()

        click()

        getrestaurant()

    }

    private fun getrestaurant() {

        homeActivityViewModel?.getRestaurant()

    }

    private fun click() {
        binding.drawer.layoutContactus.setOnClickListener {
            val intent = Intent(this, ContactUsActivity::class.java)
            startActivity(intent)
        }
        binding.drawer.layoutAboutus.setOnClickListener {
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
        }
        binding.drawer.layoutProfile.setOnClickListener {
            val intent = Intent(this, MyProfileActivity::class.java)
            startActivity(intent)
        }
        binding.txtSellAllFoodlove.setOnClickListener {
            val intent = Intent(this, ResturantActivity::class.java)
            startActivity(intent)
        }
        binding.txtSellAllSpecial.setOnClickListener {
            val intent = Intent(this, SpecialOrdesActivity::class.java)
            startActivity(intent)
        }
        binding.drawer.layoutCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setuptoolbar() {
        setSupportActionBar(binding.toolbar)
        Objects.requireNonNull(supportActionBar)!!.setDisplayShowTitleEnabled(false)
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)

        binding.toolbar.setNavigationOnClickListener { view: View? ->
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)

            }
        }
    }


    private fun initialize() {

        homeActivityViewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)

        homeActivityViewModel!!.restaurantLiveData.observe(this) {

            val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
            binding.recRestaurants.layoutManager = layoutManager
            restaurantAdapter =
                    RestaurantAdapter(this,null)
            binding.recRestaurants.adapter = restaurantAdapter

            recyclerSwitcher(1)
        }
        homeActivityViewModel!!.countryhomelivedata.observe(this) {

            val layoutManager = LinearLayoutManager(this)
            binding.recSpecialRestaurant.layoutManager = layoutManager
            val adapter =
                    SpecialOrderAdapter(this,null)
            binding.recSpecialRestaurant.adapter = adapter

        }

    }

    fun recyclerSwitcher(seconds: Int) {
        timer = Timer() // At this line a new Thread will be created
        timer!!.scheduleAtFixedRate(RecyclerTask(), 0, seconds * 3500.toLong()) // delay
        //         in
//         milliseconds
    }


    internal inner class RecyclerTask : TimerTask() {
        override fun run() {

            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            runOnUiThread {
                if (recommendCount > restaurantAdapter!!.itemCount - 1) { // In my case the number of pages are 5
                    recommendCount = 0
                } else {
                    binding.recRestaurants.post {
                        // Call smooth scroll
                        binding.recRestaurants.smoothScrollToPosition(recommendCount)
                    }
                    recommendCount++
                }

            }
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}


