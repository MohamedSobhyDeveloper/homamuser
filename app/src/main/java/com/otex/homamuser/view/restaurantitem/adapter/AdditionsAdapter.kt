package com.otex.homamuser.view.restaurantitem.adapter

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
import com.otex.homamuser.view.restaurantitem.model.Addition


class AdditionsAdapter(private val context: Context, val list: List<Addition>,val clicvalue:Clickvaluelistener)
    : RecyclerView.Adapter<AdditionsAdapter.MyViewHolder>() {

     val listid= ArrayList<Addition>()

    @SuppressLint("NewApi", "SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.txtNameFood.text=list.get(position).name
        holder.binding.txtSalary.text= list.get(position).price.toString()+" "+"ج"

        holder.binding.conAddition.setOnClickListener {
//            selectedItemPosition=position
            list[position].isChecked = !list[position].isChecked
               for (i in list.indices){
                   if (list[i].isChecked){
                       listid?.add(list[i])
                   }
               }

            clicvalue.click(listid!!)


            notifyDataSetChanged()
        }
        if(list[position].isChecked) {
            PrefsUtil.with(context).add(Constant.additionId,list[position].id).apply()
            holder.binding.conAddition.setBackgroundResource(R.drawable.circleback)
            holder.binding.txtNameFood.setTextColor(context.getColor(R.color.white))
            holder.binding.txtSalary.setTextColor(context.getColor(R.color.white))
        }
        else{
            holder.binding.conAddition.setBackgroundResource(R.drawable.rounddrawablewhiteoval)
            holder.binding.txtNameFood.setTextColor(context.getColor(R.color.black))
            holder.binding.txtSalary.setTextColor(context.getColor(R.color.orangefateh))

        }


    }





    override fun getItemCount(): Int {
        return list.size
    }




    interface Clickvaluelistener{
        fun click(idlist:ArrayList<Addition>)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_addition, parent, false)
        )
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemAdditionBinding.bind(view)
    }
}