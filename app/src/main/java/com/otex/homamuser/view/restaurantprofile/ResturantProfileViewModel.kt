package com.otex.homamuser.view.restaurantprofile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResturantProfileViewModel : ViewModel() {


    var restaurantProfilelivedata = MutableLiveData<String>()

    fun getRestaurant(){

        restaurantProfilelivedata.postValue("Egypt")
    }
}