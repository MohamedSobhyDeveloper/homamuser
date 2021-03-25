package com.otex.homamuser.view.restaurantitem

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.databinding.ActivityRestaurantItemBinding
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.cart.CartActivity
import com.otex.homamuser.view.restaurantitem.model.Addition
import com.otex.homamuser.view.restaurantitem.model.Option
import com.otex.homamuser.view.restaurantitem.model.Product
import com.otex.homamuser.view.restaurantprofile.RestaurantProfileActivity
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.AdditionsAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.ChooseSizeAdapter

class RestaurantItemActivity : BaseActivity() {
    lateinit var binding: ActivityRestaurantItemBinding
    var num:Int=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        click()
    }
    @SuppressLint("SetTextI18n")
    private fun click() {
        binding.numOrder.setText((num++).toString())
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
            binding.txtSalary.setText((120*num).toString()+"ج")
        }
        binding.minues.setOnClickListener {
            binding.numOrder.setText((num--).toString())
            binding.txtSalary.setText((120*num).toString()+"ج")
        }

    }
    private fun initialize() {

            val list = intent.getParcelableExtra<Product>("menuItemList")

            binding.foodName.text= list?.name
            binding.description.text=list?.description

            setUpRecyclerSize(list?.options!!)

            setUpRecyclerAdditions(list.additions)





    }

    private fun setUpRecyclerAdditions(additions: List<Addition>) {

        val layoutManager_horizontal = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerAddition.layoutManager = layoutManager_horizontal
        val adapter_addition =
                AdditionsAdapter(this, additions)
        binding.recyclerAddition.adapter = adapter_addition

    }

    private fun setUpRecyclerSize(options: List<Option>) {
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerSize.layoutManager = layoutManager
        val adapter =
                ChooseSizeAdapter(this,options)
        binding.recyclerSize.adapter = adapter
    }


}