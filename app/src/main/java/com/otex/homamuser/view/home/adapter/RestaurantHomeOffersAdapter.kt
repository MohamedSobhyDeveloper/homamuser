package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.NewItemSpecialOffersBinding
import com.otex.homamuser.utlitites.Constant
import com.otex.homamuser.view.home.model.Offer
import com.otex.homamuser.view.restaurantprofile.RestaurantProfileActivity
import com.squareup.picasso.Picasso


class RestaurantHomeOffersAdapter(private val context: Context, val mList: List<Offer>)
    : RecyclerView.Adapter<RestaurantHomeOffersAdapter.MyViewHolder>() {



    @SuppressLint("Range")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.nameRestaurant.text=mList[position].restaurant.name
        holder.binding.txtDescription.text=mList[position].restaurant.address

        if(mList[position].image_path.isNotEmpty()){
            Picasso.get().load(R.drawable.offericon).into(holder.binding.imgOffer)
        }else{
            Picasso.get().load(mList[position].image_path).into(holder.binding.imgOffer)

        }

         if(mList[position].restaurant.logo.isNotEmpty()){
            Picasso.get().load(mList[position].restaurant.logo).into(holder.binding.imgRestaurant)
        }else{
             Picasso.get().load(R.drawable.pasta).into(holder.binding.imgRestaurant)
         }

        holder.binding.parentLayout.setOnClickListener {
            val intent= Intent(context, RestaurantProfileActivity::class.java)
            intent.putExtra(Constant.restID,mList[position].restaurant.id)
            intent.putExtra("image",mList[position].restaurant.image)
            intent.putExtra("isOpen",mList[position].restaurant.is_open)
            context.startActivity(intent)
        }

    }



    fun addList(list: List<Offer>) {
       // this.mList!!.toMutableList().addAll(list)
        notifyDataSetChanged()
    }




    override fun getItemCount(): Int {

        if(mList?.size==null){
            return 10
        }else {

            return mList.size
        }
    }






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.new_item_special_offers, parent, false)
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = NewItemSpecialOffersBinding.bind(view)
    }
}