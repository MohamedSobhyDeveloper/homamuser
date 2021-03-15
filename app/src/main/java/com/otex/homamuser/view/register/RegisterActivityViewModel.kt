package com.otex.homamuser.view.register

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamuser.interfaces.HandleRetrofitResp
import com.otex.homamuser.retrofit.HandelCalls
import com.otex.homamuser.utlitites.DataEnum
import com.otex.homamuser.view.register.model.ModelRegister
import com.otex.homamuser.view.restaurantprofile.model.ModelRestaurantDetails
import java.util.HashMap

class RegisterActivityViewModel : ViewModel(), HandleRetrofitResp {


    var registerLivedata = MutableLiveData<ModelRegister>()


    fun makeRegister(context: Context, meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.register.name, meMap, true, this)

    }

    override fun onResponseSuccess(flag: String?, o: Any?) {

        if(flag==DataEnum.register.name){
            val modelRegister: ModelRegister = o as ModelRegister
            registerLivedata.setValue(modelRegister)
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