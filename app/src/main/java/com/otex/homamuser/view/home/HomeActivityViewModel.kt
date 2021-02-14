package com.otex.homamuser.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otex.homamuser.view.home.model.FoodLoveModel

class HomeActivityViewModel:ViewModel() {

    @JvmField
    var restaurantLiveData = MutableLiveData<String>()

    var countryhomelivedata = MutableLiveData<String>()

    fun getRestaurant(){
        restaurantLiveData.postValue("done")
        countryhomelivedata.postValue("Egypt")
    }



}