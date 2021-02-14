package com.otex.homamuser.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeActivityViewModel:ViewModel() {

    @JvmField
    var restaurantLiveData = MutableLiveData<String>()


    fun getRestaurant(){
        restaurantLiveData.postValue("done")
    }


}