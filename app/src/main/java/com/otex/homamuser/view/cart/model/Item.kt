package com.otex.homamuser.view.cart.model

data class Item(
    val details: List<Detail>,
    val name: String,
    val note: Any,
    val quantity: Int,
    val total_price: Int,
    val id:String

)