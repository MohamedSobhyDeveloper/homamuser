package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.*
import com.otex.homamuser.view.home.model.FoodLoveModel
import com.otex.homamuser.view.restaurantitem.model.Option


class ChooseSizeAdapter(private val context: Context, val chartList: List<Option>)
    : RecyclerView.Adapter<ChooseSizeAdapter.MyViewHolder>() {


    private var selectedItemPosition: Int = 0


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.checkboc.text=chartList.get(position).name
        holder.binding.txtSalary.text= chartList.get(position).price.toString()+" "+"ج"

        holder.binding.conCheckbox.setOnClickListener {
            selectedItemPosition=position
            holder.binding.checkboc.isChecked=true
            notifyDataSetChanged()
        }
        if(selectedItemPosition == position) {
            holder.binding.checkboc.isChecked=true
        }
        else{
            holder.binding.checkboc.isChecked=false
        }





    }



    fun addList(movielist: MutableList<FoodLoveModel>) {

        //this.chartList?.addAll(movielist)
        notifyDataSetChanged()
    }




    override fun getItemCount(): Int {
        if (chartList?.size== null) {
            return 5
        } else {
            return chartList.size
        }
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_size, parent, false)
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemSizeBinding.bind(view)
    }
}