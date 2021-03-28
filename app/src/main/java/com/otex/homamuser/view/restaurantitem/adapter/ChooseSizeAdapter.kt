package com.softray_solutions.newschoolproject.ui.activities.chart.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otex.homamuser.R
import com.otex.homamuser.databinding.*
import com.otex.homamuser.utlitites.Constant
import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.restaurantitem.model.Option


class ChooseSizeAdapter(private val context: Context, val list: List<Option>,val clicvalue:Clickvaluelistener)
    : RecyclerView.Adapter<ChooseSizeAdapter.MyViewHolder>() {


    private var selectedItemPosition: Int = 0


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.checkboc.text= list[position].name
        holder.binding.txtSalary.text= list[position].price.toString()+" "+"ج"

        holder.binding.conCheckbox.setOnClickListener {
            selectedItemPosition=position
            notifyDataSetChanged()
        }

        if(selectedItemPosition == position) {
            clicvalue.click(list[position].price)
            holder.binding.selectedsize.setBackgroundResource(R.drawable.circleback)
            PrefsUtil.with(context).add(Constant.optionId,list[position].id).apply()
        }
        else{
            holder.binding.selectedsize.setBackgroundResource(R.drawable.rounddrawablegrayoval)

        }

    }



    fun addList(list: MutableList<Option>) {
        this.list.toMutableList().addAll(list)
        notifyDataSetChanged()
    }




    override fun getItemCount(): Int {
        return list.size
    }

    interface Clickvaluelistener{
        fun click(price:Int)
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