package com.otex.homamuser.view.myorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityMyOrderListBinding
import com.otex.homamuser.databinding.ActivityResturantBinding
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.home.HomeActivity
import com.otex.homamuser.view.home.HomeActivityViewModel
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.CountrySpecialAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.MyOrderListAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.SpecialOrderAdapter

class MyOrderListActivity : BaseActivity() {
    private lateinit var binding : ActivityMyOrderListBinding

    private var myOrderViewModel : MyOrderViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMyOrderListBinding.inflate(layoutInflater)
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

      myOrderViewModel?.getRestaurant()

    }
    private fun initialize() {

        myOrderViewModel = ViewModelProvider(this).get(MyOrderViewModel::class.java)

        myOrderViewModel!!.myOrderViewModel.observe(this) {

            val layoutManager = LinearLayoutManager(this)
            binding.recOrder.layoutManager = layoutManager
            val adapter =
                    MyOrderListAdapter(this,null)
            binding.recOrder.adapter = adapter

        }


    }
}