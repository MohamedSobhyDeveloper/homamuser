package com.otex.homamuser.view.cart

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.databinding.ActivityCartBinding
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.myorder.MyOrderMapActivity
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.CartAdapter
import com.squareup.picasso.Picasso

class CartActivity : BaseActivity() {
    private var cartViewModel : CartViewModel? = null
    lateinit var binding: ActivityCartBinding
    var total:String=""
    var restname:String=""
    var restId:String=""
    var restlogo:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        getcart()
        click()

    }

    private fun getcart() {

        cartViewModel?.getMyCart(this,null)

    }
    private fun click() {

        binding.backbtn.setOnClickListener {
            finish()
        }
        binding.conOrderNow.setOnClickListener {
            val intent = Intent(this, MyOrderMapActivity::class.java)
            intent.putExtra("total",total)
            intent.putExtra("restname",restname)
            intent.putExtra("restId",restId)
            intent.putExtra("restlogo",restlogo)

            startActivity(intent)
        }

    }

    private fun initialize() {
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        cartViewModel!!.mycartlivedata.observe(this) {

            if (it.data != null) {
                Picasso.get().load(it.data.restaurant_logo).into(binding.logo)
                restlogo=it.data.restaurant_logo
                restId = it.data.restaurant_id
                binding.restName.text = it.data.restaurant
                binding.txtPriceTotalFirst.text = it.data.total.toString()
                binding.txtPriceTotalEnd.text = it.data.total.toString()
                binding.txtConNumorder.text = it.data.items.size.toString()
                binding.txtConTotal.text = it.data.total.toString()
                total = it.data.total.toString()
                restname = it.data.restaurant
                val layoutManager = LinearLayoutManager(this)
                binding.recOrderCart.layoutManager = layoutManager
                val adapter =
                    CartAdapter(this, it.data.items)
                binding.recOrderCart.adapter = adapter


            }else{
                   binding.nestedScroll.visibility=View.GONE
                binding.conOrderNow.visibility=View.GONE
                binding.emptycart.visibility=View.VISIBLE

            }
        }
    }

}