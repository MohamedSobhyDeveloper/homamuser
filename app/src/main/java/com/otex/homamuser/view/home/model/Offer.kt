package com.otex.homamuser.view.home.model

data class Offer(
    val description: String,
    val id: Int,
    val image_path: String,
    val name: String,
    val restaurant: Restaurant
)