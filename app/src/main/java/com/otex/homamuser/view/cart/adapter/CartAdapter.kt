package com.otex.homamuser.view.cart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ItemCartBinding
import com.otex.homamuser.view.cart.model.Item
import com.otex.homamuser.view.home.model.FoodLoveModel
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.AdditionalCartAdapter


class CartAdapter(private val context: Context, val cartList: List<Item>,val clicvalue: Clickvaluelistener)
    : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.binding.txtNumOrder.text= cartList[position].quantity.toString()
        holder.binding.txtNameOrder.text=cartList[position].name
        holder.binding.txtPriceOrder.text= cartList[position].total_price.toString()

        val layoutManager = LinearLayoutManager(context)
       holder.binding.recAdditionalItem.layoutManager = layoutManager
        val adapter =
            AdditionalCartAdapter(context,cartList[position].details)
       holder.binding.recAdditionalItem.adapter = adapter

        holder.binding.deleteItem.visibility=View.VISIBLE
        holder.binding.deleteItem.setOnClickListener {
            clicvalue.click("1")
        }



    }



    fun addList(movielist: MutableList<FoodLoveModel>) {

       // this.chartList?.addAll(movielist)
        notifyDataSetChanged()
    }




    override fun getItemCount(): Int {

            return cartList?.size!!

    }


    interface Clickvaluelistener{
        fun click(id:String)
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