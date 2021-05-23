package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ItemSpecialOffersBinding
import com.otex.homamuser.utlitites.Constant
import com.otex.homamuser.view.home.model.Data
import com.otex.homamuser.view.home.model.FoodLoveModel
import com.otex.homamuser.view.restaurant.ResturantActivity
import com.otex.homamuser.view.restaurantprofile.RestaurantProfileActivity
import com.squareup.picasso.Picasso


class RestaurantHomeAdapter(private val context: Context, val mList: MutableList<Data>)
    : RecyclerView.Adapter<RestaurantHomeAdapter.MyViewHolder>() {



    @SuppressLint("Range", "NewApi")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        if(mList[position].is_open_value==1){
            holder.binding.btnOpen.setTextColor(context.getColor(R.color.green))
            holder.binding.btnOpen.text=context.getString(R.string.open)
        }else{
            holder.binding.btnOpen.setTextColor(context.getColor(R.color.red))
            holder.binding.btnOpen.text=context.getString(R.string.close)
        }
        holder.binding.nameRestaurant.text=mList[position].name
        holder.binding.txtAddress.text=mList[position].address

        if(mList[position].image.isNotEmpty()){
            Picasso.get().load(mList[position].image).into(holder.binding.imgOffer)
        }else{
            Picasso.get().load(mList[position].image).into(holder.binding.imgOffer)

        }

         if(mList[position].logo.isNotEmpty()){
            Picasso.get().load(mList[position].logo).into(holder.binding.imgRestaurant)
        }else{
             Picasso.get().load(R.drawable.pasta).into(holder.binding.imgRestaurant)

         }

        holder.binding.parentLayout.setOnClickListener {
            val intent=Intent(context, RestaurantProfileActivity::class.java)
            intent.putExtra(Constant.restID,mList[position].id)
            intent.putExtra("image",mList[position].image)
            intent.putExtra("isOpen",mList[position].is_open_value.toString())
            context.startActivity(intent)
        }

    }



    fun addList(list: MutableList<Data>) {
        this.mList.addAll(list)
        notifyDataSetChanged()
    }




    override fun getItemCount(): Int {

            return mList?.size!!

    }






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_special_offers, parent, false)
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemSpecialOffersBinding.bind(view)
    }
}