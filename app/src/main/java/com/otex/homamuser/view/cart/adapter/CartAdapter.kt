package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ItemCartBinding
import com.otex.homamuser.databinding.ItemCountryHomeBinding
import com.otex.homamuser.view.home.model.FoodLoveModel


class CartAdapter(private val context: Context, val chartList: MutableList<FoodLoveModel>?)
    : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {



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





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false)
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCartBinding.bind(view)
    }
}