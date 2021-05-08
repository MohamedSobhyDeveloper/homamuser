package com.otex.homamuser.retrofit

import com.otex.homamuser.view.cart.model.ModelCart
import com.otex.homamuser.view.cart.modeldelete.ModelDelete
import com.otex.homamuser.view.editprofile.model.ModelUpdateProfile
import com.otex.homamuser.view.home.model.ModelHomeScreen
import com.otex.homamuser.view.login.model.ModelLogin
import com.otex.homamuser.view.myorder.model.ModelFees
import com.otex.homamuser.view.myorder.model.ModelMakeOrder
import com.otex.homamuser.view.myorder.myorderModel.ModelMyOrderList
import com.otex.homamuser.view.myprofile.model.ModelProfile
import com.otex.homamuser.view.orderdetail.model.ModelOrderDetails
import com.otex.homamuser.view.register.model.ModelRegister
import com.otex.homamuser.view.register.modelactivate.ModelActivate
import com.otex.homamuser.view.restaurantitem.ModelResponeBasket
import com.otex.homamuser.view.restaurantitem.model.ModelRestaurantMenu
import com.otex.homamuser.view.restaurantprofile.model.ModelRestaurantDetails
import retrofit2.Call
import retrofit2.http.*
import java.util.*


interface ApiCall {

    @GET("services/rest/?")
    fun getPhotos(@QueryMap requestBody: HashMap<String, String?>?): Call<String?>?


    @GET("user/restaurants/{restaurant_id}")
    fun getRestaurantDetails(@Path("restaurant_id") id: String?):Call<ModelRestaurantDetails?>?

    @GET("user/home/{category_id}")
    fun getRestaurantHome(@Path("category_id") id: String?):Call<ModelHomeScreen?>?

    @GET("user/restaurants/{restaurant_id}/{menu_id}")
    fun getRestaurantMenu(@Path("restaurant_id") id: String?,@Path("menu_id") menu_id: String?):Call<ModelRestaurantMenu?>?

    @FormUrlEncoded
    @POST("user/login")
    fun login(@FieldMap map: HashMap<String, String?>?): Call<ModelLogin?>?

    @FormUrlEncoded
    @POST("user/register")
    fun register(@FieldMap map: HashMap<String, String?>?): Call<ModelRegister?>?

    @FormUrlEncoded
    @POST("user/update-profile")
    fun updateProfile(@FieldMap map: HashMap<String, String?>?): Call<ModelUpdateProfile?>?

    @GET
    fun getURL(@Url url: String?): Call<ModelHomeScreen?>?

    @FormUrlEncoded
    @POST("user/restaurants/{restaurant_id}/basket")
    fun addToBasket(@FieldMap map: HashMap<String, String?>?,@Path("restaurant_id") id: String?): Call<ModelResponeBasket?>?

    @GET("user/profile")
    fun myProfile():Call<ModelProfile?>?

    @GET("user/cart")
    fun myCart():Call<ModelCart?>?

    @FormUrlEncoded
    @POST("user/make_order")
    fun makeorder(@FieldMap map: HashMap<String, String?>?): Call<ModelMakeOrder?>?

    @FormUrlEncoded
    @POST("user/restaurants/{restaurant_id}/shipping-fees")
    fun getFees(@FieldMap map: HashMap<String, String?>?,@Path("restaurant_id") id: String?): Call<ModelFees?>?


    @GET("user/orders")
    fun myOrderList():Call<ModelMyOrderList?>?

    @GET
    fun getURLMyOrder(@Url url: String?): Call<ModelMyOrderList?>?

    @GET("user/order/{order_id}")
    fun orderDetails(@Path("order_id") id: String?):Call<ModelOrderDetails?>?


    @FormUrlEncoded
    @POST("user/contact-us")
    fun contactUs(@FieldMap map: HashMap<String, String?>?): Call<ModelMakeOrder?>?

    @FormUrlEncoded
    @POST("user/activate")
    fun activePhone(@FieldMap map: HashMap<String, String?>?): Call<ModelActivate?>?

    @FormUrlEncoded
    @POST("user/forget-password")
    fun userForgetPassword(@FieldMap map: HashMap<String, String?>?): Call<ModelActivate?>?

    @FormUrlEncoded
    @POST("user/reset")
    fun userResetPassword(@FieldMap map: HashMap<String, String?>?): Call<ModelActivate?>?

    @FormUrlEncoded
    @POST("user/update-password")
    fun userUpdatePassword(@FieldMap map: HashMap<String, String?>?): Call<ModelActivate?>?

    @POST("user/delete/cart/{cart_id}")
    fun deleteItem(@Path("cart_id") id: String?): Call<ModelDelete?>?


}