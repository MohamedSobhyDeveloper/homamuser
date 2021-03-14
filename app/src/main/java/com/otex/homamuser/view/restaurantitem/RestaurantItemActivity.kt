package com.otex.homamuser.view.restaurantitem

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.databinding.ActivityRestaurantItemBinding
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.cart.CartActivity
import com.otex.homamuser.view.restaurantitem.model.Addition
import com.otex.homamuser.view.restaurantitem.model.Option
import com.otex.homamuser.view.restaurantitem.model.Product
import com.otex.homamuser.view.restaurantprofile.RestaurantProfileActivity
import com.otex.homamuser.view.restaurantprofile.ResturantProfileViewModel
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.AdditionsAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.ChooseSizeAdapter

class RestaurantItemActivity : BaseActivity() {
    private var resturantMenuViewModel: ResturantProfileViewModel? = null
    lateinit var binding: ActivityRestaurantItemBinding
    var num:Int=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val i = intent
        var id=i.extras?.getString("position")
        val list: ArrayList<Product>? = i
                .getSerializableExtra("menuItemList") as ArrayList<Product>?
        initialize(list,id)
        click()
    }
    private fun click() {
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
    private fun initialize(list: ArrayList<Product>?, id: String?) {

            binding.foodName.text= list!!.get(id!!.toInt()).toString()
         //   binding.description.text=list!![id!!.toInt()].description

            setUpRecyclerSize(list!![id!!.toInt()].options)

            setUpRecyclerAdditions(list!![id!!.toInt()].additions)





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