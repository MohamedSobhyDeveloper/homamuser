package com.otex.homamuser.view.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamuser.interfaces.HandleRetrofitResp
import com.otex.homamuser.retrofit.HandelCalls
import com.otex.homamuser.utlitites.DataEnum
import com.otex.homamuser.view.login.model.ModelLogin
import java.util.HashMap

class LoginActivityViewModel:ViewModel(),HandleRetrofitResp {

    var loginLivedata = MutableLiveData<ModelLogin>()


    fun makeLogin(context: Context, meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.login.name, meMap, true, this)

    }




    override fun onResponseSuccess(flag: String?, o: Any?) {

        if(flag==DataEnum.login.name){
            val modelLogin: ModelLogin = o as ModelLogin
            loginLivedata.setValue(modelLogin)
        }
    }

    override fun onResponseFailure(flag: String?, o: String?) {
    }

    override fun onNoContent(flag: String?, code: Int) {
    }

    override fun onBadRequest(flag: String?, o: Any?) {
    }

}