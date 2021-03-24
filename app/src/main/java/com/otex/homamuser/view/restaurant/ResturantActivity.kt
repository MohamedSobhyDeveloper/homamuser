package com.otex.homamuser.view.restaurant

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.databinding.ActivityResturantBinding
import com.otex.homamuser.utlitites.Constant
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.home.HomeActivity
import com.otex.homamuser.view.home.HomeActivityViewModel
import com.otex.homamuser.view.home.model.Category
import com.otex.homamuser.view.home.model.Data
import com.otex.homamuser.view.restaurantprofile.`interface`.OnItemClick
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.CategoryHomeAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.CountrySpecialAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.RestaurantHomeAdapter
import ru.alexbykov.nopaginate.callback.OnLoadMoreListener
import ru.alexbykov.nopaginate.paginate.NoPaginate
import java.util.*

class ResturantActivity : BaseActivity() ,OnItemClick{
    private lateinit var binding : ActivityResturantBinding

    private var homeActivityViewModel : HomeActivityViewModel? = null

    var categoryAdapter: CategoryHomeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResturantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        getrestaurant()
        click()
    }
    private fun click() {
        binding.backbtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun getrestaurant() {
        val map = HashMap<String, String?>()
        map.put(Constant.categoryID,intent.getStringExtra(Constant.categoryID))
       homeActivityViewModel?.getRestaurant_category(this,map)

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
        binding.recRestaurants.layoutManager = layoutManager
        val adapter =
            RestaurantHomeAdapter(this,restaurants)
        binding.recRestaurants.adapter = adapter
    }

    private fun setupRecyclerCategoryHome(categories: List<Category>) {
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        binding.recCountry.layoutManager = layoutManager
        val adapter =
            CountrySpecialAdapter(this,categories,this)
        binding.recCountry.adapter = adapter
    }

    override fun onClick(value: String?) {
        setupResOfCategory(value)
    }

    private fun setupResOfCategory(value: String?) {
        val map = HashMap<String, String?>()
        map.put(Constant.categoryID,value)
        homeActivityViewModel?.getRestaurant_category(this,map)
    }

}