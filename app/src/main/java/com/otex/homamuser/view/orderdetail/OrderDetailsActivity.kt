package com.otex.homamuser.view.orderdetail

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityOrderDetailsBinding
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.cart.CartViewModel
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.OrderDatailsAdapter
import com.squareup.picasso.Picasso
import java.util.HashMap

class OrderDetailsActivity : BaseActivity() {
    private var cartViewModel : CartViewModel? = null
    lateinit var binding: ActivityOrderDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initialize()
        getcart()
        click()

    }

    private fun getcart() {

        val map = HashMap<String, String?>()
        map.put("order_id",intent.getStringExtra("order_id"))
        cartViewModel?.getOrderDetails(this,map)

    }
    private fun click() {

        binding.backbtn.setOnClickListener {
         finish()
        }

    }

    private fun initialize() {
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        cartViewModel!!.orderdtailslivedata.observe(this) {

            binding.restName.text=it.data.restaurant
            binding.txtPriceTotalFirst.text=it.data.total.toString()
            binding.txtPriceTotalEnd.text=it.data.total.toString()

            if (it.data.restaurant_logo.isNotEmpty()){
                Picasso.get().load(it.data.restaurant_logo).into(binding.imgLogo)

            }else{
                Picasso.get().load(R.drawable.pasta).into(binding.imgLogo)

            }

            val layoutManager = LinearLayoutManager(this)
            binding.recOrderDetails.layoutManager = layoutManager
            val adapter =
                    OrderDatailsAdapter(this,it.data.items)
            binding.recOrderDetails.adapter = adapter

        }
    }
}