package com.otex.homamuser.view.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityHomeBinding
import com.otex.homamuser.utlitites.Constant
import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.aboutus.AboutUsActivity
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.cart.CartActivity
import com.otex.homamuser.view.contactus.ContactUsActivity
import com.otex.homamuser.view.home.model.Category
import com.otex.homamuser.view.home.model.Data
import com.otex.homamuser.view.home.model.Offer
import com.otex.homamuser.view.login.LoginActivity
import com.otex.homamuser.view.myorder.MyOrderListActivity
import com.otex.homamuser.view.myprofile.MyProfileActivity
import com.otex.homamuser.view.restaurant.ResturantActivity
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.CategoryHomeAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.RestaurantHomeAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.RestaurantHomeOffersAdapter
import com.tbruyelle.rxpermissions2.RxPermissions
import java.util.*


class HomeActivity : BaseActivity() {

    private lateinit var binding : ActivityHomeBinding

    private var homeActivityViewModel : HomeActivityViewModel? = null

    var timer: Timer? = null
    var recommendCount = 0
    var restaurantAdapter: CategoryHomeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPermission()

        initialize()

        setuptoolbar()

        click()

        getRestaurant()

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
                    Toast.makeText(this, "Allow App to Access Your Location", Toast.LENGTH_SHORT).show()
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri: Uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                    // Oups permission denied
                }
            }
    }

    private fun getRestaurant() {
        val map = HashMap<String, String?>()
        map.put("category_id", "0")
        homeActivityViewModel?.getRestaurant_category(this, map)

    }

    private fun click() {

        binding.drawer.logout.setOnClickListener {
            PrefsUtil.with(this).add(Constant.token, "").apply()
            val intent =Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
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
            val intent=Intent(this, ResturantActivity::class.java)
            intent.putExtra(Constant.categoryID, "0")
            startActivity(intent)
        }
        binding.txtSellAllSpecial.setOnClickListener {
            val intent=Intent(this, ResturantActivity::class.java)
            intent.putExtra(Constant.categoryID, "0")
            startActivity(intent)
        }
        binding.drawer.layoutCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        binding.drawer.layoutOrders.setOnClickListener {
            startActivity(Intent(this, MyOrderListActivity::class.java))
        }
    }

    private fun setuptoolbar() {
        setSupportActionBar(binding.toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayShowTitleEnabled(false)
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



//        binding.indicator.attachToRecyclerView(binding.newRecSpecialRestaurantHome)
        homeActivityViewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)

        homeActivityViewModel!!.restaurantCategoryLiveData.observe(this) {

            setupRecyclerCategoryHome(it.categories)
            setupRecyclerRestaurantHome(it.restaurants.data)
            setUpOffers(it.offers)

        }


    }

    private fun setUpOffers(offers: List<Offer>) {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.newRecSpecialRestaurantHome.layoutManager = layoutManager
        val adapter =
                RestaurantHomeOffersAdapter(this, offers)
        binding.newRecSpecialRestaurantHome.adapter = adapter
    }


    private fun setupRecyclerRestaurantHome(restaurants: List<Data>) {
        val layoutManager = LinearLayoutManager(this)
        binding.recSpecialRestaurantHome.layoutManager = layoutManager
        val adapter =
                RestaurantHomeAdapter(this, restaurants)
        binding.recSpecialRestaurantHome.adapter = adapter
    }

    private fun setupRecyclerCategoryHome(categories: List<Category>) {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recCategoryHome.layoutManager = layoutManager
        restaurantAdapter =
                CategoryHomeAdapter(this, categories)
        binding.recCategoryHome.adapter = restaurantAdapter
    }


    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}


