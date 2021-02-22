package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ItemCountryHomeBinding
import com.otex.homamuser.databinding.ItemSpecialOffersBinding
import com.otex.homamuser.view.forgetpassword.ActivityForgetPassword
import com.otex.homamuser.view.home.model.FoodLoveModel
import com.otex.homamuser.view.register.RegisterActivity
import com.otex.homamuser.view.restaurantprofile.RestaurantProfileActivity


class SpecialOrderAdapter(private val context: Context, val chartList: MutableList<FoodLoveModel>?)
    : RecyclerView.Adapter<SpecialOrderAdapter.MyViewHolder>() {



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding?.btnOpen?.setOnClickListener {

             context.startActivity(Intent(context, RestaurantProfileActivity::class.java))


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
                LayoutInflater.from(context).inflate(R.layout.item_special_offers, parent, false)
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemSpecialOffersBinding.bind(view)
    }
}