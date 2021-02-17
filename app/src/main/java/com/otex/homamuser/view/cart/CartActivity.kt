package com.otex.homamuser.view.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityCartBinding
import com.otex.homamuser.databinding.ActivityLoginBinding
import com.otex.homamuser.view.forgetpassword.ActivityForgetPassword
import com.otex.homamuser.view.home.HomeActivity
import com.otex.homamuser.view.login.LoginActivityViewModel
import com.otex.homamuser.view.myorder.MyOrderMapActivity
import com.otex.homamuser.view.register.RegisterActivity
import com.otex.homamuser.view.restaurant.ResturantActivity
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.CartAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.FoodLoveAdapter

class CartActivity : AppCompatActivity() {
    private var cartViewModel : CartViewModel? = null
    lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        getcart()
        click()

    }

    private fun getcart() {

        cartViewModel?.getcart()

    }
    private fun click() {

        binding.backbtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        binding.conOrderNow.setOnClickListener {
            val intent = Intent(this, MyOrderMapActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initialize() {
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        cartViewModel!!.cartlivedata.observe(this) {

            val layoutManager = LinearLayoutManager(this)
            binding.recOrderCart.layoutManager = layoutManager
            val adapter =
                    CartAdapter(this,null)
            binding.recOrderCart.adapter = adapter

        }
    }

}