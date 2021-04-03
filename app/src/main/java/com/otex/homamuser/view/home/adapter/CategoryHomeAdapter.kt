package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ItemCountryHomeBinding
import com.otex.homamuser.utlitites.Constant
import com.otex.homamuser.view.home.model.Category
import com.otex.homamuser.view.home.model.FoodLoveModel
import com.otex.homamuser.view.restaurant.ResturantActivity
import com.squareup.picasso.Picasso


class CategoryHomeAdapter(private val context: Context, private val mList: List<Category>)
    : RecyclerView.Adapter<CategoryHomeAdapter.MyViewHolder>() {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.nameCategory.text=mList[position].name
        if(mList[position].image.isNotEmpty()){
            Picasso.get().load(mList[position].image).into(holder.binding.imgDepartment)
        }
        holder.binding.categoryHome.setOnClickListener {
            val intent=Intent(context,ResturantActivity::class.java)
            intent.putExtra(Constant.categoryID,mList[position].id)
            context.startActivity(intent)

        }

    }



    fun addList(movielist: MutableList<Category>) {

       // this.chartList?.addAll(movielist)
        notifyDataSetChanged()
    }




    override fun getItemCount(): Int {

            return mList.size

    }






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_country_home, parent, false)
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCountryHomeBinding.bind(view)
    }
}