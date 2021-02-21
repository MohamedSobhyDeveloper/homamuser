package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.*
import com.otex.homamuser.view.forgetpassword.ActivityForgetPassword
import com.otex.homamuser.view.home.model.FoodLoveModel
import com.otex.homamuser.view.register.RegisterActivity
import com.otex.homamuser.view.restaurantprofile.RestaurantProfileActivity


class AdditionsAdapter(private val context: Context, val chartList: MutableList<FoodLoveModel>?)
    : RecyclerView.Adapter<AdditionsAdapter.MyViewHolder>() {
    private var selectedItemPosition: Int = 0

    var itemBinding: ItemAdditionBinding?=null


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.conAddition.setOnClickListener {
            selectedItemPosition=position
            holder.binding.conAddition.setBackgroundResource(R.drawable.circleback)
            notifyDataSetChanged()
        }
        if(selectedItemPosition == position) {
            holder.binding.conAddition.setBackgroundResource(R.drawable.circleback)
        }
        else{
            holder.binding.conAddition.setBackgroundResource(R.drawable.rounddrawablewhiteoval)
        }


    }



    fun addList(movielist: MutableList<FoodLoveModel>) {

        this.chartList?.addAll(movielist)
        notifyDataSetChanged()
    }




    override fun getItemCount(): Int {
        if (chartList?.size== null) {
            return 5
        } else {
            return chartList?.size!!
        }
    }




    class MyViewHolder(var binding: ItemAdditionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return MyViewHolder(ItemAdditionBinding.inflate(layoutInflater))
    }
}