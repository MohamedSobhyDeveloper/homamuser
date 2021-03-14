package com.otex.homamuser.view.restaurantprofile.model

data class Data(
        val address: String,
        val district: String,
        val id: Int,
        val logo: String,
        val menus: List<Menu>,
        val name: String
)