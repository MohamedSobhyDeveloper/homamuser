package com.otex.homamuser.view.orderdetail

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.databinding.ActivityOrderDetailsBinding
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.cart.CartViewModel
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.CartAdapter

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

    //    cartViewModel?.getMyCart(this,null)

    }
    private fun click() {

        binding.backbtn.setOnClickListener {
         finish()
        }

    }

    private fun initialize() {
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
//        cartViewModel!!.cartlivedata.observe(this) {
//
//            val layoutManager = LinearLayoutManager(this)
//            binding.recOrderCart.layoutManager = layoutManager
//            val adapter =
//                    CartAdapter(this,null)
//            binding.recOrderCart.adapter = adapter
//
//        }
    }
}