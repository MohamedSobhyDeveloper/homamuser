package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ItemCountryHomeBinding
import com.otex.homamuser.databinding.ItemMenuBinding
import com.otex.homamuser.databinding.ItemSpecialOffersBinding
import com.otex.homamuser.view.forgetpassword.ActivityForgetPassword
import com.otex.homamuser.view.home.model.FoodLoveModel
import com.otex.homamuser.view.register.RegisterActivity
import com.otex.homamuser.view.restaurantprofile.RestaurantProfileActivity
import com.otex.homamuser.view.restaurantprofile.`interface`.OnItemClick


class MenuResProfileAdapter(private val context: Context, val chartList: MutableList<FoodLoveModel>?,var onclik:OnItemClick)
    : RecyclerView.Adapter<MenuResProfileAdapter.MyViewHolder>() {

    var itemBinding: ItemMenuBinding?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        itemBinding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)


         return MyViewHolder(itemBinding!!)


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


         itemBinding?.conMenu?.setOnClickListener {

             onclik.onClick("best dishes")
             itemBinding?.conMenu?.setBackgroundResource(R.drawable.drawable_circle_fayroze)
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



    class MyViewHolder(private val itemBinding: ItemMenuBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {



    }
}