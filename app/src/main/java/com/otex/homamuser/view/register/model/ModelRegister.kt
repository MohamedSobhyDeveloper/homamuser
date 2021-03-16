package com.otex.homamuser.view.register.model

data class ModelRegister(
    val expires_in: Int,
    val token: String,
    val token_type: String,
    val user: User
)