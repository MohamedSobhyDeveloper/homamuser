package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ItemBestdishesBinding
import com.otex.homamuser.view.restaurantitem.RestaurantItemActivity
import com.otex.homamuser.view.restaurantitem.model.Product
import com.squareup.picasso.Picasso


class BestDishesAdapter(private val context: Context, var menuItemList: ArrayList<Product>)
    : RecyclerView.Adapter<BestDishesAdapter.MyViewHolder>() {



    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.productName.text=menuItemList[position].name
        holder.binding.description.text=menuItemList[position].description


        holder.binding.imgAdd.setOnClickListener {

            val intent=Intent(context,RestaurantItemActivity::class.java)
            intent.putExtra("menuItemList", menuItemList[position])
            intent.putExtra("position", position.toString())
            context.startActivity(intent)

        }


    }



//    fun addList(movielist: MutableList<FoodLoveModel>) {
//
//        this.menuItemList?.addAll(movielist)
//        notifyDataSetChanged()
//    }




    override fun getItemCount(): Int {


            return menuItemList.size

    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_bestdishes, parent, false)
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemBestdishesBinding.bind(view)
    }
}