package com.otex.homamuser.view.editprofile

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamuser.interfaces.HandleRetrofitResp
import com.otex.homamuser.retrofit.HandelCalls
import com.otex.homamuser.utlitites.DataEnum
import com.otex.homamuser.view.editprofile.model.ModelUpdateProfile
import com.otex.homamuser.view.home.model.ModelHomeScreen
import java.util.HashMap

class EditProfileViewModel : ViewModel(),HandleRetrofitResp {

    var updateProfileLiveData = MutableLiveData<ModelUpdateProfile>()

    fun updateProfile(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.updateProfile.name, meMap, true, this)
    }

    override fun onResponseSuccess(flag: String?, o: Any?) {
        if(flag==DataEnum.updateProfile.name){
            val modelUpdateProfile: ModelUpdateProfile = o as ModelUpdateProfile
            updateProfileLiveData.setValue(modelUpdateProfile)
        }
    }

    override fun onResponseFailure(flag: String?, o: String?) {
    }

    override fun onNoContent(flag: String?, code: Int) {
    }

    override fun onBadRequest(flag: String?, o: Any?) {
    }

}