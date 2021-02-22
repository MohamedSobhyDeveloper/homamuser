package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ItemBestdishesBinding
import com.otex.homamuser.databinding.ItemCountryHomeBinding
import com.otex.homamuser.databinding.ItemMenuBinding
import com.otex.homamuser.view.home.model.FoodLoveModel
import com.otex.homamuser.view.restaurantprofile.`interface`.OnItemClick


class MenuResProfileAdapter(private val context: Context, val chartList: MutableList<FoodLoveModel>?,var onclik:OnItemClick)
    : RecyclerView.Adapter<MenuResProfileAdapter.MyViewHolder>() {

    private var selectedItemPosition: Int = 0

    @SuppressLint("ResourceAsColor", "NewApi")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


         holder.binding?.conMenu?.setOnClickListener {
              selectedItemPosition=position
             notifyDataSetChanged()
             onclik.onClick("best dishes")
             holder.binding.conMenu.setBackgroundResource(R.drawable.backgmenu)
             holder.binding.txtMenu.setTextColor(context.getColor(R.color.white))

         }
        if(selectedItemPosition == position) {
            holder.binding.conMenu.setBackgroundResource(R.drawable.backgmenu)
            holder.binding.txtMenu.setTextColor(context.getColor(R.color.white))
        }
        else{
            holder.binding.conMenu.setBackgroundResource(R.drawable.round_circle_fayroze)
            holder.binding.txtMenu.setTextColor(context.getColor(R.color.eyecolor))
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
                LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false)
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMenuBinding.bind(view)
    }
}