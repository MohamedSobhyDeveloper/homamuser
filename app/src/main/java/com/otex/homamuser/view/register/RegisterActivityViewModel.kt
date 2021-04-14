package com.otex.homamuser.view.register

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamuser.interfaces.HandleRetrofitResp
import com.otex.homamuser.retrofit.HandelCalls
import com.otex.homamuser.utlitites.DataEnum
import com.otex.homamuser.view.register.model.ModelRegister
import com.otex.homamuser.view.register.modelactivate.ModelActivate
import java.util.HashMap

class RegisterActivityViewModel : ViewModel(), HandleRetrofitResp {


    var registerLivedata = MutableLiveData<ModelRegister>()
    var activeLivedata = MutableLiveData<ModelActivate>()


    fun makeRegister(context: Context, meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.register.name, meMap, true, this)

    }

    fun activePhone(context: Context, meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.activeCode.name, meMap, true, this)

    }


    override fun onResponseSuccess(flag: String?, o: Any?) {

        if(flag==DataEnum.register.name){
            val modelRegister: ModelRegister = o as ModelRegister
            registerLivedata.setValue(modelRegister)
        }else if (flag==DataEnum.activeCode.name){

        }

    }

    override fun onResponseFailure(flag: String?, o: String?) {
    }

    override fun onNoContent(flag: String?, code: Int) {
    }

    override fun onBadRequest(flag: String?, o: Any?) {
    }


}