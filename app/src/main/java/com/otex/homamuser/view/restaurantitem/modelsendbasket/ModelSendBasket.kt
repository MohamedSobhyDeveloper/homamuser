package com.otex.homamuser.view.restaurantitem.modelsendbasket

data class ModelSendBasket(
    val product_id: String,
    val quantity: String,
    val options: List<String>,
    val additions: List<String>,
)