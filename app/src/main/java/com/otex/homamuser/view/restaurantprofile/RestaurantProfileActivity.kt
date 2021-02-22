package com.otex.homamuser.view.restaurantprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.databinding.ActivityRestaurantProfileBinding
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.home.model.FoodLoveModel
import com.otex.homamuser.view.login.LoginActivity
import com.otex.homamuser.view.myorder.MyOrderListActivity
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
        initialize()
        click()
        getrestaurant()
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

            val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            binding.recMenu.layoutManager = layoutManager
            val adapter =
                MenuResProfileAdapter(this,null,this)
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

    private fun getrestaurant() {

        resturantProfileViewModel?.getRestaurant()

    }

    override fun onClick(value: String?) {
        val layoutManager = LinearLayoutManager(this)
        binding.recBestdishes.layoutManager = layoutManager
        val adapter =
                BestDishesAdapter(this, null)
        binding.recBestdishes.adapter = adapter
    }


}