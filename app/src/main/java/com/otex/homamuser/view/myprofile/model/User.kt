package com.otex.homamuser.view.myprofile.model

data class User(
    val created_at: String,
    val email: String,
    val email_verified: Int,
    val email_verified_at: Any,
    val id: Int,
    val name: String,
    val phone: String,
    val status: Int,
    val updated_at: String
)