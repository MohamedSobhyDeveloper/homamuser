package com.otex.homamuser.view.restaurantitem

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamuser.interfaces.HandleRetrofitResp
import com.otex.homamuser.retrofit.HandelCalls
import com.otex.homamuser.utlitites.DataEnum
import java.util.HashMap

class ResturantItemViewModel : ViewModel() ,HandleRetrofitResp{


    var basketlivedata = MutableLiveData<ModelResponeBasket>()




    fun addToBasket(context: Context,meMap: HashMap<String, String?>?){

        HandelCalls.getInstance(context)?.call(DataEnum.addToBasket.name, meMap, true, this)

    }
    override fun onResponseSuccess(flag: String?, o: Any?) {

        if (flag == DataEnum.addToBasket.name) {
            val modelResponeBasket: ModelResponeBasket = o as ModelResponeBasket
            basketlivedata.setValue(modelResponeBasket)
        }

    }

    override fun onResponseFailure(flag: String?, o: String?) {
    }

    override fun onNoContent(flag: String?, code: Int) {
    }

    override fun onBadRequest(flag: String?, o: Any?) {
    }
}