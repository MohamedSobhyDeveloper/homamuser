package com.otex.homamuser.view.restaurantprofile

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamuser.interfaces.HandleRetrofitResp
import com.otex.homamuser.retrofit.HandelCalls
import com.otex.homamuser.utlitites.DataEnum
import com.otex.homamuser.view.restaurantitem.model.ModelRestaurantMenu
import com.otex.homamuser.view.restaurantprofile.model.ModelRestaurantDetails
import java.util.HashMap

class ResturantProfileViewModel : ViewModel() ,HandleRetrofitResp{


    var restaurantProfilelivedata = MutableLiveData<ModelRestaurantDetails>()
    var restaurantMenudata = MutableLiveData<ModelRestaurantMenu>()



    fun getRestaurantDetails(context: Context,meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.RestaurantDetails.name, meMap, true, this)

    }
    fun getRestaurantMenuItem(context: Context,meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.RestMenu.name, meMap, true, this)

    }
    override fun onResponseSuccess(flag: String?, o: Any?) {

        if (flag == DataEnum.RestaurantDetails.name) {
            val modelRestaurantDetails: ModelRestaurantDetails = o as ModelRestaurantDetails
            restaurantProfilelivedata.setValue(modelRestaurantDetails)
        }else if(flag==DataEnum.RestMenu.name){
            val modelRestaurantMenu: ModelRestaurantMenu = o as ModelRestaurantMenu
            restaurantMenudata.value = modelRestaurantMenu
        }

    }

    override fun onResponseFailure(flag: String?, o: String?) {
    }

    override fun onNoContent(flag: String?, code: Int) {
    }

    override fun onBadRequest(flag: String?, o: Any?) {
    }
}