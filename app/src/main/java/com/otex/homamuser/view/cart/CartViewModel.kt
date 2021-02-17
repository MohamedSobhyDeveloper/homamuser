package com.otex.homamuser.view.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {

    var cartlivedata = MutableLiveData<String>()

    fun getcart(){
        cartlivedata.postValue("done")
    }


}