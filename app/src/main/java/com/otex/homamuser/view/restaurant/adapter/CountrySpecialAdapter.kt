package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.databinding.ItemCountryHomeBinding
import com.otex.homamuser.databinding.ItemCountryResturantBinding
import com.otex.homamuser.view.home.model.FoodLoveModel


class CountrySpecialAdapter(private val context: Context, val chartList: MutableList<FoodLoveModel>?)
    : RecyclerView.Adapter<CountrySpecialAdapter.MyViewHolder>() {

    var itemBinding: ItemCountryResturantBinding?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        itemBinding = ItemCountryResturantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
         return MyViewHolder(itemBinding!!)


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {




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



    class MyViewHolder(private val itemBinding: ItemCountryResturantBinding) : RecyclerView.ViewHolder(itemBinding.root) {



    }
}