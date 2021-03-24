package com.otex.homamuser.retrofit

import com.otex.homamuser.view.login.model.ModelLogin
import com.otex.homamuser.view.register.model.ModelRegister
import com.otex.homamuser.view.restaurantitem.model.ModelRestaurantMenu
import com.otex.homamuser.view.restaurantprofile.model.ModelRestaurantDetails
import retrofit2.Call
import retrofit2.http.*
import java.util.*


interface ApiCall {

    @GET("services/rest/?")
    fun getPhotos(@QueryMap requestBody: HashMap<String, String?>?): Call<String?>?


    @GET("restaurants/{id}")
    fun getRestaurantDetails(@Path("id") id: String?):Call<ModelRestaurantDetails?>?


    @GET("restaurants/{restaurant_id}/{menu_id}")
    fun getRestaurantMenu(@Path("restaurant_id") id: String?,@Path("menu_id") menu_id: String?):Call<ModelRestaurantMenu?>?

    @FormUrlEncoded
    @POST("user/login")
    fun login(@FieldMap map: HashMap<String, String?>?): Call<ModelLogin?>?

    @FormUrlEncoded
    @POST("user/register")
    fun register(@FieldMap map: HashMap<String, String?>?): Call<ModelRegister?>?

    @GET
    fun getUsers(@Url url: String?): Call<ModelRegister?>?


}