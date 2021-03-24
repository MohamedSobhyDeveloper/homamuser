package com.otex.homamuser.view.login.model

data class ModelLogin(
    val expires_in: Int,
    val token: String,
    val token_type: String,
    val user: User
)