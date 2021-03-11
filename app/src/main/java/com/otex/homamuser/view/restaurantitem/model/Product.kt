package com.otex.homamuser.view.restaurantitem.model

data class Product(
    val additions: List<Addition>,
    val description: String,
    val id: Int,
    val image_path: Any,
    val name: String,
    val options: List<Option>
)