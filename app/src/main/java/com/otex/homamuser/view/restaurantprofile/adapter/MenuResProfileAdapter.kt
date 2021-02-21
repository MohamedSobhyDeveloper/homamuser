package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ItemCountryHomeBinding
import com.otex.homamuser.databinding.ItemMenuBinding
import com.otex.homamuser.view.home.model.FoodLoveModel
import com.otex.homamuser.view.restaurantprofile.`interface`.OnItemClick


class MenuResProfileAdapter(private val context: Context, val chartList: MutableList<FoodLoveModel>?,var onclik:OnItemClick)
    : RecyclerView.Adapter<MenuResProfileAdapter.MyViewHolder>() {

    private var selectedItemPosition: Int = 0

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


         holder.binding?.conMenu?.setOnClickListener {
              selectedItemPosition=position
             notifyDataSetChanged()
             onclik.onClick("best dishes")
             holder.binding.conMenu.setBackgroundResource(R.drawable.backgmenu)
             holder.binding.txtMenu.setTextColor(R.color.white)

         }
        if(selectedItemPosition == position) {
            holder.binding.conMenu.setBackgroundResource(R.drawable.backgmenu)
            holder.binding.txtMenu.setTextColor(R.color.white)
        }
        else{
            holder.binding.conMenu.setBackgroundResource(R.drawable.round_circle_fayroze)
            holder.binding.txtMenu.setTextColor(R.color.eyecolor)
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


    class MyViewHolder(var binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return MyViewHolder(ItemMenuBinding.inflate(layoutInflater))
    }
}