package com.otex.homamuser.view.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityHomeBinding
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

        homeActivityViewModel!!.restaurantLiveData.observe(this, {


        })

    }




    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}


