package com.otex.homamuser.utlitites

import android.content.Context

class UserInfo(context: Context) {

    var sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    var editor = sharedPreferences.edit()

    fun setUserSignSate(signstate: Boolean) {
        editor.putBoolean("sign_state",signstate)
        editor.apply()
    }

    fun getUserSignState(): Boolean {
        return sharedPreferences.getBoolean("sign_state", false)!!
    }
}