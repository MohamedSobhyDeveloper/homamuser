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


class ChooseSizeAdapter(private val context: Context, val chartList: MutableList<FoodLoveModel>?)
    : RecyclerView.Adapter<ChooseSizeAdapter.MyViewHolder>() {


    private var selectedItemPosition: Int = 0


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.conCheckbox.setOnClickListener {
            selectedItemPosition=position
            holder.binding.checkboc.isChecked=true
            notifyDataSetChanged()
        }
        if(selectedItemPosition == position) {
            holder.binding.checkboc.isChecked=true
        }
        else{
            holder.binding.checkboc.isChecked=false
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
            return chartList.size
        }
    }




    class MyViewHolder(var binding: ItemSizeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return MyViewHolder(ItemSizeBinding.inflate(layoutInflater))
    }
}