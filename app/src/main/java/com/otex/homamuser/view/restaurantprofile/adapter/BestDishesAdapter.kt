package com.otex.homamuser.view.restaurantprofile.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ItemBestdishesBinding
import com.otex.homamuser.utlitites.Constant
import com.otex.homamuser.view.restaurantitem.RestaurantItemActivity
import com.otex.homamuser.view.restaurantitem.model.Product


class BestDishesAdapter(private val context: Context, var menuItemList: ArrayList<Product>, val restauranrId:String)
    : RecyclerView.Adapter<BestDishesAdapter.MyViewHolder>() {



    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.productName.text=menuItemList[position].name
        holder.binding.description.text=menuItemList[position].description

        holder.binding.price.text= menuItemList[position].options[0].price.toString()+" ج" +" : "+
                                          menuItemList[position].options[menuItemList[position]
                                                  .options.size-1].price.toString()+"ج"

//        if(menuItemList[position].image_path!=null){
//            Picasso.get().load(menuItemList[position].image_path).into(holder.binding.imgFood)
//        }
        holder.binding.imgAdd.setOnClickListener {

            val intent=Intent(context,RestaurantItemActivity::class.java)
            intent.putExtra("menuItemList", menuItemList[position])
            intent.putExtra(Constant.restID, restauranrId)
            context.startActivity(intent)

        }
        holder.binding.conParent.setOnClickListener {

            val intent=Intent(context,RestaurantItemActivity::class.java)
            intent.putExtra("menuItemList", menuItemList[position])
            intent.putExtra(Constant.restID, restauranrId)
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