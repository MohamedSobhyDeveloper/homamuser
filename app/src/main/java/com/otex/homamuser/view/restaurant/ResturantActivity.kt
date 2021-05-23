package com.otex.homamuser.view.restaurant

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
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
import java.util.*

class ResturantActivity : BaseActivity() ,OnItemClick{
    private lateinit var binding : ActivityResturantBinding

    private var homeActivityViewModel : HomeActivityViewModel? = null

    var categoryAdapter: CategoryHomeAdapter? = null
    private var loading = true
    private var nextPage = ""
    var adapter:RestaurantHomeAdapter?=null
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


        binding.nestedScroll!!.viewTreeObserver.addOnScrollChangedListener {
            val view = binding.nestedScroll!!.getChildAt(binding.nestedScroll!!.childCount - 1)
            val diff = view.bottom - (binding.nestedScroll!!.height + binding.nestedScroll!!
                    .getScrollY())
            if (loading) {
                if (diff == 0) {
                    setupPagination()
                    loading = false
                }
            }
        }

        homeActivityViewModel!!.restaurantCategoryLiveData.observe(this) {

            setupRecyclerCategoryHome(it.categories)
            setupRecyclerRestaurantHome(it.restaurants.data)

            if(it.restaurants.next_page_url!=null && it.restaurants.next_page_url .isNotEmpty() ){

                loading=true
                nextPage=it.restaurants.next_page_url

            }

        }

        homeActivityViewModel!!.urlPaginationLiveData.observe(this) {

          adapter?.addList(it.restaurants.data)

            if(it.restaurants.next_page_url!=null && it.restaurants.next_page_url .isNotEmpty() ){

                loading=true
                nextPage=it.restaurants.next_page_url

            }

        }

    }
    private fun setupPagination() {
        val map = HashMap<String, String?>()
        map.put("url",nextPage)
        homeActivityViewModel?.getUrlPagination(this,map)

    }
    private fun setupRecyclerRestaurantHome(restaurants: MutableList<Data>) {
        val layoutManager = LinearLayoutManager(this)
        binding.recRestaurants.layoutManager = layoutManager
        binding.recRestaurants.setNestedScrollingEnabled(false);
         adapter =
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

    override fun onClick(value: String?, name: String?) {
        setupResOfCategory(value)
    }

    private fun setupResOfCategory(value: String?) {
        val map = HashMap<String, String?>()
        map.put(Constant.categoryID,value)
        homeActivityViewModel?.getRestaurant_category(this,map)
    }

}