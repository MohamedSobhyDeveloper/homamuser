package com.otex.homamuser.view.restaurantitem

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.databinding.ActivityRestaurantItemBinding
import com.otex.homamuser.utlitites.Constant
import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.restaurantitem.model.Addition
import com.otex.homamuser.view.restaurantitem.model.Option
import com.otex.homamuser.view.restaurantitem.model.Product
import com.otex.homamuser.view.restaurantprofile.RestaurantProfileActivity
import com.otex.homamuser.view.restaurantitem.adapter.AdditionsAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.ChooseSizeAdapter
import es.dmoral.toasty.Toasty

class RestaurantItemActivity : BaseActivity() {
    lateinit var binding: ActivityRestaurantItemBinding
    var num:Int=1
    var productId=""
    private var resturantItemViewModel: ResturantItemViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        click()
    }
    @SuppressLint("SetTextI18n")
    private fun click() {
        binding.quantity.text = (num++).toString()
        binding.backbtn.setOnClickListener {
            startActivity(Intent(this, RestaurantProfileActivity::class.java))
            finish()
        }
        binding.addToCart.setOnClickListener {
//            startActivity(Intent(this,CartActivity::class.java))
//            finish()
            val map = HashMap<String, String?>()
            map.put("restId",intent.getStringExtra(Constant.restID)!!)
            map.put("product_id",productId)
            map.put("quantity",binding.quantity.text.toString())
            if (!PrefsUtil.with(this).get(Constant.optionId,"0").equals("0")){
                map.put("options[0]",PrefsUtil.with(this).get(Constant.optionId,"0"))

            }
            if (!PrefsUtil.with(this).get(Constant.additionId,"0").equals("0")){
                map.put("additions[0]",PrefsUtil.with(this).get(Constant.additionId,"0"))

            }

            resturantItemViewModel?.addToBasket(this,map)

        }
        binding.add.setOnClickListener {
            binding.quantity.text = (num++).toString()
            binding.txtSalary.text = (120*num).toString()+"ج"
        }
        binding.minues.setOnClickListener {
            binding.quantity.text = (num--).toString()
            binding.txtSalary.text = (120*num).toString()+"ج"
        }

    }
    private fun initialize() {
        PrefsUtil.with(this).add(Constant.additionId,"0").apply()
        PrefsUtil.with(this).add(Constant.optionId,"0").apply()


        resturantItemViewModel = ViewModelProvider(this).get(ResturantItemViewModel::class.java)
        resturantItemViewModel!!.basketlivedata.observe(this) {


            Toasty.success(this, it.message, Toast.LENGTH_SHORT, true).show()


        }


            val list = intent.getParcelableExtra<Product>("menuItemList")
            productId= list?.id.toString()
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