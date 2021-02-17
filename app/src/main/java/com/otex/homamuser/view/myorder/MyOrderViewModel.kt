package com.otex.homamuser.view.myorder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyOrderViewModel : ViewModel() {

    var myOrderViewModel = MutableLiveData<String>()

    fun getRestaurant(){
        myOrderViewModel.postValue("done")
    }
    fun getprice(){
        myOrderViewModel.postValue("done")
    }
}