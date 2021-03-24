package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ItemCountryResturantBinding
import com.otex.homamuser.view.home.model.Category
import com.otex.homamuser.view.home.model.FoodLoveModel
import com.otex.homamuser.view.restaurantprofile.`interface`.OnItemClick


class CountrySpecialAdapter(private val context: Context, val chartList: List<Category>, var onclik: OnItemClick)
    : RecyclerView.Adapter<CountrySpecialAdapter.MyViewHolder>() {



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

              holder.binding.nameCountry.text=chartList[position].name

        holder.binding.conCountry.setOnClickListener {
            notifyDataSetChanged()
            onclik.onClick(chartList[position].id)
        }


    }



    fun addList(movielist: MutableList<FoodLoveModel>) {

      //  this.chartList?.addAll(movielist)
        notifyDataSetChanged()
    }




    override fun getItemCount(): Int {

            return chartList?.size!!

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_country_resturant, parent, false)
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCountryResturantBinding.bind(view)
    }
}