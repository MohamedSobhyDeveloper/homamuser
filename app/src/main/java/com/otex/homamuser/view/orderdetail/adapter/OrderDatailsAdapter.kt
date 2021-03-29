package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ItemCartBinding
import com.otex.homamuser.view.home.model.FoodLoveModel
import com.otex.homamuser.view.orderdetail.model.Item


class OrderDatailsAdapter(private val context: Context,  val mList: List<Item>)
    : RecyclerView.Adapter<OrderDatailsAdapter.MyViewHolder>() {



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.binding.txtNumOrder.text= mList[position].quantity.toString()
        holder.binding.txtNameOrder.text=mList[position].name
        holder.binding.txtPriceOrder.text= mList[position].total_price.toString()

        val layoutManager = LinearLayoutManager(context)
       holder.binding.recAdditionalItem.layoutManager = layoutManager
        val adapter =
            AdditionalCartOrderDetailsAdapter(context,mList[position].details)
       holder.binding.recAdditionalItem.adapter = adapter



    }



    fun addList(movielist: MutableList<FoodLoveModel>) {

       // this.chartList?.addAll(movielist)
        notifyDataSetChanged()
    }




    override fun getItemCount(): Int {

            return mList?.size!!

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