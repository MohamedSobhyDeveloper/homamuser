package com.otex.homamuser.view.contactus

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamuser.interfaces.HandleRetrofitResp
import com.otex.homamuser.retrofit.HandelCalls
import com.otex.homamuser.utlitites.DataEnum
import com.otex.homamuser.view.myorder.model.ModelMakeOrder
import java.util.HashMap

class ContactUsViewModel : ViewModel(), HandleRetrofitResp {
    @JvmField
    var contactsLiveData = MutableLiveData<ModelMakeOrder>()


    fun contactUs(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.homerestaurant.name, meMap, true, this)
    }

    override fun onResponseSuccess(flag: String?, o: Any?) {
        if(flag==DataEnum.contactUs.name){
            val modelMakeOrder: ModelMakeOrder = o as ModelMakeOrder
            contactsLiveData.setValue(modelMakeOrder)
        }
    }

    override fun onResponseFailure(flag: String?, o: String?) {
    }

    override fun onNoContent(flag: String?, code: Int) {
    }

    override fun onBadRequest(flag: String?, o: Any?) {
    }
}