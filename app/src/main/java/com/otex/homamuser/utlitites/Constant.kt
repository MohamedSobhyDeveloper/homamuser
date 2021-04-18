package com.otex.homamuser.utlitites

import android.content.Context
import android.content.pm.PackageManager

/**
 * Created by Mina Shaker on 27-Mar-18.
 */
class Constant {
    //    public static String apiValue="e4bbe5b7a4c1eb55652965aee885dd59bd2ee7f4";
    var apiKey = "ApiKey"
    var Authorization = "Authorization"


    companion object {
        var deletUriKey = "delete_key"
        var SharedPreferencePriorite = "sharedPreferencePriorite"
        var SharedPreferenceStatus = "sharedPreferenceStatus"
        private var context: Context? = null
        private var instance: Constant? = null
        const val subUrl = "api/"
        var SparkAppSharedPreference = "SharedPrefernceKeyForSparkApp"
        @JvmField
        var apiValue = "b639949727a9411e069f841b9e730e82"

        //online
        @JvmField
        var baseUrl ="http://homam-ly.com/api/"

        var token = "token"
        var email = "email"
        var phone = "phone"
        var username = "username"
        var userid = "userId"
        var categoryID = "category_id"
        var restID = "id"
        var optionId = "optionId"
        var additionId = "additionId"




        fun getInstance(context: Context?): Constant? {
            Companion.context = context
            if (instance == null) {
                instance = Constant()
            }
            return instance
        }

        fun getVestionCode(c: Context): String {

            var v = ""
            try {
                v += c.packageManager.getPackageInfo(c.packageName, 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return v
        }
    }
}