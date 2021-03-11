package com.otex.homamuser.view.restaurantprofile

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamuser.interfaces.HandleRetrofitResp
import com.otex.homamuser.retrofit.HandelCalls
import com.otex.homamuser.utlitites.DataEnum
import com.otex.homamuser.view.restaurantitem.model.ModelRestaurantMenu
import com.otex.homamuser.view.restaurantprofile.model.ModelRestaurantDetails

class ResturantProfileViewModel : ViewModel() ,HandleRetrofitResp{


    var restaurantProfilelivedata = MutableLiveData<ModelRestaurantDetails>()
    var restaurantMenudata = MutableLiveData<ModelRestaurantMenu>()


  fun  getRestauran(context: Context){

  }
    fun getItemMenu(context: Context){

        HandelCalls.getInstance(context)?.call(DataEnum.menuDetails.name, null, true, this)

    }
    fun getRestaurantMenu(context: Context){

        HandelCalls.getInstance(context)?.call(DataEnum.RestMenu.name, null, true, this)

    }
    override fun onResponseSuccess(flag: String?, o: Any?) {

        if (flag == DataEnum.menuDetails.name) {
            val modelRestaurantDetails: ModelRestaurantDetails = o as ModelRestaurantDetails
            restaurantProfilelivedata.setValue(modelRestaurantDetails)
        }else if(flag==DataEnum.RestMenu.name){
            val modelRestaurantMenu: ModelRestaurantMenu = o as ModelRestaurantMenu
            restaurantMenudata.setValue(modelRestaurantMenu)
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