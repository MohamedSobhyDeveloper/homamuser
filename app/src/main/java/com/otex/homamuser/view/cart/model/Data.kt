package com.otex.homamuser.view.cart.model

data class Data(
    val date: String,
    val items: List<Item>,
    val restaurant: String,
    val restaurant_id: String,
    val restaurant_logo: String,
    val total: Int
)