package com.otex.homamuser.view.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityHomeBinding
import com.otex.homamuser.view.aboutus.AboutUsActivity
import com.otex.homamuser.view.cart.CartActivity
import com.otex.homamuser.view.contactus.ContactUsActivity
import com.otex.homamuser.view.myprofile.MyProfileActivity
import com.otex.homamuser.view.restaurant.ResturantActivity
import com.otex.homamuser.view.specialorder.SpecialOrdesActivity
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.FoodLoveAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.SpecialOrderAdapter
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    private var homeActivityViewModel : HomeActivityViewModel? = null


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
            binding.recFoodLOve.layoutManager = layoutManager
            val adapter =
                    FoodLoveAdapter(this,null)
            binding.recFoodLOve.adapter = adapter
           hanldeAutoScroll()

        }
        homeActivityViewModel!!.countryhomelivedata.observe(this) {

            val layoutManager = LinearLayoutManager(this)
            binding.recSpecialOrders.layoutManager = layoutManager
            val adapter =
                    SpecialOrderAdapter(this,null)
            binding.recSpecialOrders.adapter = adapter

        }

    }




    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    private fun hanldeAutoScroll() {
        var position = 0
        val duration = 2000
        //final int pixelsToMove = 90;
        val mHandler = Handler(Looper.getMainLooper())
        val SCROLLING_RUNNABLE: Runnable = object : Runnable {
            override fun run() {
                position++
                if (position <=position) {
                    binding.recFoodLOve.scrollToPosition(position)
                } else if (position == binding.recFoodLOve.size) {
                    position = -1
                }

                // recyclerView.smoothScrollBy(pixelsToMove, 0);
                mHandler.postDelayed(this, 2000)
            }
        }
        mHandler.postDelayed(SCROLLING_RUNNABLE, 2000)
    }
}


