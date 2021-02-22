package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ItemCountryHomeBinding
import com.otex.homamuser.databinding.ItemCountryResturantBinding
import com.otex.homamuser.databinding.ItemMyorderBinding
import com.otex.homamuser.databinding.ItemSpecialOffersBinding
import com.otex.homamuser.view.home.model.FoodLoveModel
import com.otex.homamuser.view.myorder.MyOrderListActivity
import com.otex.homamuser.view.orderdetail.OrderDetailsActivity
import com.otex.homamuser.view.restaurantitem.RestaurantItemActivity


class MyOrderListAdapter(private val context: Context, val chartList: MutableList<FoodLoveModel>?)
    : RecyclerView.Adapter<MyOrderListAdapter.MyViewHolder>() {



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.binding?.btnOrdernow?.setOnClickListener {
           context.startActivity(Intent(context, OrderDetailsActivity::class.java))
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






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_myorder, parent, false)
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMyorderBinding.bind(view)
    }
}