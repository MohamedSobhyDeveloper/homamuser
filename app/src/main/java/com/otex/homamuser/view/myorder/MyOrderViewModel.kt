package com.otex.homamuser.view.myorder

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamuser.interfaces.HandleRetrofitResp
import com.otex.homamuser.retrofit.HandelCalls
import com.otex.homamuser.utlitites.DataEnum
import com.otex.homamuser.view.editprofile.model.ModelUpdateProfile
import com.otex.homamuser.view.myorder.model.ModelMakeOrder
import com.otex.homamuser.view.myorder.myorderModel.Data
import com.otex.homamuser.view.myorder.myorderModel.ModelMyOrderList
import java.util.HashMap

class MyOrderViewModel : ViewModel(),HandleRetrofitResp {

    var makeOrderlivedtat = MutableLiveData<ModelMakeOrder>()
    var myOrderListViewModel = MutableLiveData<ModelMyOrderList>()
    var urlOrderListViewModel = MutableLiveData<ModelMyOrderList>()


    fun makeOrder(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.makeOrder.name, meMap, true, this)
    }

    fun myOrderList(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.myOrderList.name, meMap, true, this)
    }

    fun UrlOrderList(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.urlMyOrderList.name, meMap, true, this)
    }

    override fun onResponseSuccess(flag: String?, o: Any?) {
        if(flag==DataEnum.makeOrder.name){
            val makeOrder: ModelMakeOrder = o as ModelMakeOrder
            makeOrderlivedtat.setValue(makeOrder)
        }else if(flag==DataEnum.myOrderList.name){
                val myOrderList: ModelMyOrderList = o as ModelMyOrderList
                myOrderListViewModel.setValue(myOrderList)
        }else if(flag==DataEnum.urlMyOrderList.name){
            val myOrderList: ModelMyOrderList = o as ModelMyOrderList
            urlOrderListViewModel.setValue(myOrderList)
        }
    }

    override fun onResponseFailure(flag: String?, o: String?) {
        TODO("Not yet implemented")
    }

    override fun onNoContent(flag: String?, code: Int) {
        TODO("Not yet implemented")
    }

    override fun onBadRequest(flag: String?, o: Any?) {
        TODO("Not yet implemented")
    }
}