package com.otex.homamuser.view.cart

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamuser.interfaces.HandleRetrofitResp
import com.otex.homamuser.retrofit.HandelCalls
import com.otex.homamuser.utlitites.DataEnum
import com.otex.homamuser.view.cart.model.ModelCart
import com.otex.homamuser.view.myprofile.model.ModelProfile
import com.otex.homamuser.view.orderdetail.model.ModelOrderDetails
import java.util.HashMap

class CartViewModel : ViewModel(),HandleRetrofitResp {

    var mycartlivedata = MutableLiveData<ModelCart>()
    var orderdtailslivedata = MutableLiveData<ModelOrderDetails>()

    fun getMyCart(context: Context, meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.mycart.name, meMap, true, this)
    }
    fun getOrderDetails(context: Context, meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.orderDetails.name, meMap, true, this)
    }

    override fun onResponseSuccess(flag: String?, o: Any?) {
        if(flag==DataEnum.mycart.name){
            val modelCart: ModelCart = o as ModelCart
            mycartlivedata.value = modelCart
        }else if(flag==DataEnum.orderDetails.name){
            val modelOrderDetails: ModelOrderDetails = o as ModelOrderDetails
            orderdtailslivedata.value = modelOrderDetails
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