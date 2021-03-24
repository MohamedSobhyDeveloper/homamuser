package com.otex.homamuser.view.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamuser.interfaces.HandleRetrofitResp
import com.otex.homamuser.retrofit.HandelCalls
import com.otex.homamuser.utlitites.DataEnum
import com.otex.homamuser.view.home.model.FoodLoveModel
import com.otex.homamuser.view.home.model.ModelHomeScreen
import com.otex.homamuser.view.login.model.ModelLogin
import java.util.HashMap

class HomeActivityViewModel:ViewModel(),HandleRetrofitResp {

    @JvmField
    var restaurantCategoryLiveData = MutableLiveData<ModelHomeScreen>()


    fun getRestaurant_category(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.homerestaurant.name, meMap, true, this)
    }

    override fun onResponseSuccess(flag: String?, o: Any?) {
        if(flag==DataEnum.homerestaurant.name){
            val modelHomeScreen: ModelHomeScreen = o as ModelHomeScreen
            restaurantCategoryLiveData.setValue(modelHomeScreen)
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