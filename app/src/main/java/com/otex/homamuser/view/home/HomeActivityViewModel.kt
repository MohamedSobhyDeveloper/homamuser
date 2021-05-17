package com.otex.homamuser.view.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamuser.interfaces.HandleRetrofitResp
import com.otex.homamuser.retrofit.HandelCalls
import com.otex.homamuser.utlitites.DataEnum
import com.otex.homamuser.view.cart.model.ModelCart
import com.otex.homamuser.view.home.model.FoodLoveModel
import com.otex.homamuser.view.home.model.ModelHomeScreen
import com.otex.homamuser.view.login.model.ModelLogin
import java.util.HashMap

class HomeActivityViewModel:ViewModel(),HandleRetrofitResp {

    @JvmField
    var restaurantCategoryLiveData = MutableLiveData<ModelHomeScreen>()
    var mycartlivedata = MutableLiveData<ModelCart>()
    var urlPaginationLiveData = MutableLiveData<ModelHomeScreen>()

    fun getRestaurant_category(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.homerestaurant.name, meMap, true, this)
    }
    fun getUrlPagination(context: Context, meMap: HashMap<String, String?>?){
        HandelCalls.getInstance(context)?.call(DataEnum.URLpagination.name, meMap, true, this)
    }

    fun getMyCart(context: Context, meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.mycart.name, meMap, true, this)
    }

    override fun onResponseSuccess(flag: String?, o: Any?) {
        if(flag==DataEnum.homerestaurant.name){
            val modelHomeScreen: ModelHomeScreen = o as ModelHomeScreen
            restaurantCategoryLiveData.setValue(modelHomeScreen)
        }else if(flag==DataEnum.URLpagination.name){
            val modelHomeScreen: ModelHomeScreen = o as ModelHomeScreen
            urlPaginationLiveData.setValue(modelHomeScreen)
        }else if(flag==DataEnum.mycart.name){
            val modelCart: ModelCart = o as ModelCart
            mycartlivedata.value = modelCart
        }
    }

    override fun onResponseFailure(flag: String?, o: String?) {
    }

    override fun onNoContent(flag: String?, code: Int) {
    }

    override fun onBadRequest(flag: String?, o: Any?) {
    }


}