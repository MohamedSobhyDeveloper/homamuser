package com.otex.homamuser.view.myorder.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ItemMyorderBinding
import com.otex.homamuser.view.myorder.myorderModel.Data
import com.otex.homamuser.view.orderdetail.OrderDetailsActivity
import com.squareup.picasso.Picasso


class MyOrderListAdapter(private val context: Context, val mList: List<Data>)
    : RecyclerView.Adapter<MyOrderListAdapter.MyViewHolder>() {



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


       holder.binding.restName.text=mList[position].restaurant
        holder.binding.txtOrderPrice.text=mList[position].total.toString()
        holder.binding.numDished.text=mList[position].dishes.toString()
        holder.binding.numOrder.text=mList[position].code
        holder.binding.txtDate.text=mList[position].date
        holder.binding.btnOrdernow.text=mList[position].status

        if (mList[position].restaurant_logo.isNotEmpty()){
            Picasso.get().load(mList[position].restaurant_logo).into(holder.binding.imgLogo)

        }else{
            Picasso.get().load(R.drawable.pasta).into(holder.binding.imgLogo)

        }

        holder.binding.conCart.setOnClickListener {
            var intent=Intent(context,OrderDetailsActivity::class.java)
            intent.putExtra("order_id",mList[position].id)
            context.startActivity(intent)
        }



    }



    fun addList(list: List<Data>) {

        this.mList.toMutableList().addAll(list)
        notifyDataSetChanged()
    }




    override fun getItemCount(): Int {

            return mList?.size!!

    }






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_myorder, parent, false)
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMyorderBinding.bind(view)
    }
}