package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
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


    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.conAddition.setOnClickListener {
            selectedItemPosition=position
            holder.binding.conAddition.setBackgroundResource(R.drawable.circleback)
            holder.binding.txtNameFood.setTextColor(context.getColor(R.color.white))
            holder.binding.txtSalary.setTextColor(context.getColor(R.color.white))
            notifyDataSetChanged()
        }
        if(selectedItemPosition == position) {
            holder.binding.conAddition.setBackgroundResource(R.drawable.circleback)
            holder.binding.txtNameFood.setTextColor(context.getColor(R.color.white))
            holder.binding.txtSalary.setTextColor(context.getColor(R.color.white))
        }
        else{
            holder.binding.conAddition.setBackgroundResource(R.drawable.rounddrawablewhiteoval)
            holder.binding.txtNameFood.setTextColor(context.getColor(R.color.black))
            holder.binding.txtSalary.setTextColor(context.getColor(R.color.orangefateh))

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






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_addition, parent, false)
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemAdditionBinding.bind(view)
    }
}