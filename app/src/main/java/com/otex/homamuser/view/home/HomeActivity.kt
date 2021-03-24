package com.otex.homamuser.view.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityHomeBinding
import com.otex.homamuser.utlitites.Constant
import com.otex.homamuser.view.aboutus.AboutUsActivity
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.cart.CartActivity
import com.otex.homamuser.view.contactus.ContactUsActivity
import com.otex.homamuser.view.home.model.Category
import com.otex.homamuser.view.home.model.Data
import com.otex.homamuser.view.myprofile.MyProfileActivity
import com.otex.homamuser.view.restaurant.ResturantActivity
import com.otex.homamuser.view.specialorder.SpecialOrdesActivity
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.CategoryHomeAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.RestaurantHomeAdapter
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

        initialize()

        setuptoolbar()

        click()

        getRestaurant()

    }

    private fun getRestaurant() {
        val map = HashMap<String, String?>()
        map.put("category_id","0")
        homeActivityViewModel?.getRestaurant_category(this,map)

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
            val intent=Intent(this,ResturantActivity::class.java)
            intent.putExtra(Constant.categoryID,"0")
            startActivity(intent)
        }
        binding.txtSellAllSpecial.setOnClickListener {
            val intent=Intent(this,ResturantActivity::class.java)
            intent.putExtra(Constant.categoryID,"0")
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

        homeActivityViewModel!!.restaurantCategoryLiveData.observe(this) {

            setupRecyclerCategoryHome(it.categories)
            setupRecyclerRestaurantHome(it.restaurants.data)

        }


    }

    private fun setupRecyclerRestaurantHome(restaurants: List<Data>) {
        val layoutManager = LinearLayoutManager(this)
        binding.recSpecialRestaurantHome.layoutManager = layoutManager
        val adapter =
                RestaurantHomeAdapter(this,restaurants)
        binding.recSpecialRestaurantHome.adapter = adapter
    }

    private fun setupRecyclerCategoryHome(categories: List<Category>) {
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        binding.recCategoryHome.layoutManager = layoutManager
        restaurantAdapter =
                CategoryHomeAdapter(this,categories)
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


