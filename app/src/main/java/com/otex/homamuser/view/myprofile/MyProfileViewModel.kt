package com.otex.homamuser.view.myprofile

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamuser.interfaces.HandleRetrofitResp
import com.otex.homamuser.retrofit.HandelCalls
import com.otex.homamuser.utlitites.DataEnum
import com.otex.homamuser.view.editprofile.model.ModelUpdateProfile
import com.otex.homamuser.view.home.model.ModelHomeScreen
import com.otex.homamuser.view.myprofile.model.ModelProfile
import java.util.HashMap

class MyProfileViewModel : ViewModel(),HandleRetrofitResp {

    var myProfileLiveData = MutableLiveData<ModelProfile>()

    fun myProfile(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.myProfile.name, meMap, true, this)
    }

    override fun onResponseSuccess(flag: String?, o: Any?) {
        if(flag==DataEnum.myProfile.name){
            val modelProfile: ModelProfile = o as ModelProfile
            myProfileLiveData.setValue(modelProfile)
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