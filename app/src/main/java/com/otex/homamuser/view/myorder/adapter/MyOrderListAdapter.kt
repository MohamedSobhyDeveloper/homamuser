package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.databinding.ItemCountryHomeBinding
import com.otex.homamuser.databinding.ItemCountryResturantBinding
import com.otex.homamuser.databinding.ItemMyorderBinding
import com.otex.homamuser.view.home.model.FoodLoveModel
import com.otex.homamuser.view.myorder.MyOrderListActivity
import com.otex.homamuser.view.restaurantitem.RestaurantItemActivity


class MyOrderListAdapter(private val context: Context, val chartList: MutableList<FoodLoveModel>?)
    : RecyclerView.Adapter<MyOrderListAdapter.MyViewHolder>() {

    var itemBinding: ItemMyorderBinding?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        itemBinding = ItemMyorderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
         return MyViewHolder(itemBinding!!)


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        itemBinding?.btnOrdernow?.setOnClickListener {
           context.startActivity(Intent(context, RestaurantItemActivity::class.java))
        }


    }



    fun addList(movielist: MutableList<FoodLoveModel>) {

        this.chartList?.addAll(movielist)
        notifyDataSetChanged()
    }




    override fun getItemCount(): Int {
        if (chartList?.size== null) {
            return 10
        } else {
            return chartList?.size!!
        }
    }



    class MyViewHolder(private val itemBinding: ItemMyorderBinding) : RecyclerView.ViewHolder(itemBinding.root) {



    }
}