package com.otex.homamuser.view.forgetpassword.fragmentforgetpass

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamuser.interfaces.HandleRetrofitResp
import com.otex.homamuser.retrofit.HandelCalls
import com.otex.homamuser.utlitites.DataEnum
import com.otex.homamuser.view.login.model.ModelLogin
import com.otex.homamuser.view.register.modelactivate.ModelActivate
import java.util.HashMap

class FragmentForgetPasswordAddEmailViewModel : ViewModel(),HandleRetrofitResp {

    var forgetpassLivedata = MutableLiveData<ModelActivate>()
    var resetpassLivedata = MutableLiveData<ModelActivate>()
    var updatepassLivedata = MutableLiveData<ModelActivate>()


    fun forgerPass(context: Context, meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.forgetPass.name, meMap, true, this)

    }
    fun resetPass(context: Context, meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.resetPass.name, meMap, true, this)

    }
    fun updatefPass(context: Context, meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.updatePass.name, meMap, true, this)

    }




    override fun onResponseSuccess(flag: String?, o: Any?) {

        if(flag== DataEnum.forgetPass.name){
            val modelLogin: ModelActivate = o as ModelActivate
            forgetpassLivedata.setValue(modelLogin)
        } else if(flag== DataEnum.resetPass.name){
            val modelLogin: ModelActivate = o as ModelActivate
            resetpassLivedata.setValue(modelLogin)
        }else if(flag== DataEnum.updatePass.name){
            val modelLogin: ModelActivate = o as ModelActivate
            updatepassLivedata.setValue(modelLogin)
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