package com.otex.homamuser.retrofit

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.otex.homamuser.interfaces.HandleRetrofitResp
import com.otex.homamuser.interfaces.HandleRetrofitRespAdapter
import com.otex.homamuser.utlitites.*
import com.otex.homamuser.view.login.LoginActivity
import es.dmoral.toasty.Toasty
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

/**
 * Created by lenovo on 1/3/2018.
 */
class HandelCalls {
    private var onRespnse: HandleRetrofitResp? = null
    private var onRespnseAdapter: HandleRetrofitRespAdapter? = null

    /**
     * @param onRespnseSucess
     */
    fun setonRespnseSucess(onRespnseSucess: HandleRetrofitResp?) {
        onRespnse = onRespnseSucess
    }

    fun setonRespnseSucessApapter(onRespnseAdapter: HandleRetrofitRespAdapter?) {
        this.onRespnseAdapter = onRespnseAdapter
    }

    fun call(flag: String, meMap: HashMap<String, String?>?, ShowLoadingDialog: Boolean, onRespnseSucess: HandleRetrofitResp) {

        onRespnse = onRespnseSucess

        if(flag==DataEnum.homerestaurant.name){
            val id=meMap?.get("category_id")
            callRetrofit(restRetrofit?.getClientService()?.getRestaurantHome(id), flag, ShowLoadingDialog)

        }else if (flag == DataEnum.RestaurantDetails.name) {

            val id=meMap?.get(Constant.restID)
            callRetrofit(restRetrofit?.getClientService()?.getRestaurantDetails(id), flag, ShowLoadingDialog)
        }else if(flag==DataEnum.RestMenu.name){
           val id=meMap?.get("restId")
           val menuId=meMap?.get("menuId")

           callRetrofit(restRetrofit?.getClientService()?.getRestaurantMenu(id,menuId), flag, ShowLoadingDialog)

       }else if(flag==DataEnum.register.name){

           callRetrofit(restRetrofit?.getClientService()?.register(meMap), flag, ShowLoadingDialog)

       }else if(flag==DataEnum.login.name){

           callRetrofit(restRetrofit?.getClientService()?.login(meMap), flag, ShowLoadingDialog)

       }else if(flag==DataEnum.updateProfile.name){
            callRetrofit(restRetrofit?.getClientService()?.updateProfile(meMap), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.URLpagination.name){
            val url=meMap?.get("url")

            callRetrofit(restRetrofit?.getClientService()?.getURL(url), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.addToBasket.name){
            val id=meMap?.get("restId")

            callRetrofit(restRetrofit?.getClientService()?.addToBasket(meMap,id), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.myProfile.name){
            callRetrofit(restRetrofit?.getClientService()?.myProfile(), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.mycart.name){
            callRetrofit(restRetrofit?.getClientService()?.myCart(), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.makeOrder.name){
            callRetrofit(restRetrofit?.getClientService()?.makeorder(meMap), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.myOrderList.name){
            callRetrofit(restRetrofit?.getClientService()?.myOrderList(), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.urlMyOrderList.name){
            val url=meMap?.get("url")

            callRetrofit(restRetrofit?.getClientService()?.getURLMyOrder(url), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.orderDetails.name){
            val id=meMap?.get("order_id")
            callRetrofit(restRetrofit?.getClientService()?.orderDetails(id), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.contactUs.name){
            callRetrofit(restRetrofit?.getClientService()?.contactUs(meMap), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.activeCode.name){
            callRetrofit(restRetrofit?.getClientService()?.activePhone(meMap), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.forgetPass.name){
            callRetrofit(restRetrofit?.getClientService()?.userForgetPassword(meMap), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.resetPass.name){
            callRetrofit(restRetrofit?.getClientService()?.userResetPassword(meMap), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.updatePass.name){
            callRetrofit(restRetrofit?.getClientService()?.userUpdatePassword(meMap), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.getFees.name){
            val id=meMap?.get("id")
            callRetrofit(restRetrofit?.getClientService()?.getFees(meMap,id), flag, ShowLoadingDialog)

        }else if(flag==DataEnum.deletItem.name){
            val id=meMap?.get("id")
            callRetrofit(restRetrofit?.getClientService()?.deleteItem(id), flag, ShowLoadingDialog)
        }

    }




    fun <T> callRetrofit(call: Call<T>?, flag: String?, ShowDialog: Boolean) {
        val progressDialog = Loading(context)
        if (ShowDialog) {
            progressDialog.show()
        }
        call!!.enqueue(object : Callback<T?> {
            override fun onResponse(call: Call<T?>, response: Response<T?>) {
                Log.d("test", "onResponse() called with: call = [$call], response = [$response]response returned")
                if (ShowDialog) {
                    progressDialog.dismiss()
                }
                Log.e(TAG, "onResponse: $response")
                if (response.code() == 200) {
                    if (response.isSuccessful && response.body() != null) {
                        if (onRespnse != null) Log.d("testing", "onResponse() minma called with: call = [$call], response = [$response]")
                        onRespnse!!.onResponseSuccess(flag, response.body())
                    }
                    // TODO - 4 Add 400 to condition base on (Login Response)
                }else if (response.code() == 401){
                    PrefsUtil.with(context!!).add(Constant.token,"").apply()
                    val intent = Intent(context, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context?.startActivity(intent)
                } else if (response.code() == 400 || response.code() == 422 || response.code() == 300) {
                    Log.e("res1", "resp")
                    if (onRespnse != null) {
                        Log.e("res2", "resp")
                        try {
                            Log.e("res3", "resp")
                            // Log.e("resp",response.errorBody().string());
                            // onRespnse.onBadRequest(flag, response.errorBody().string());
                            // Log.e("resp",response.errorBody().string());
                            val o = JSONObject(response.errorBody()!!.string())
                            if (o.has("message")){
                                val message=o.getString("message")
                                if (message.isNotEmpty()){
                                    Toasty.error(context!!, message, Toast.LENGTH_SHORT, true).show()
                                }
                            }else{
                                if (o.has("erroe")){
                                    val error=o.getString("error")


                                    if (error.isNotEmpty()){
                                        Toasty.error(context!!, error, Toast.LENGTH_SHORT, true).show()

                                    }
                                }
                            }


//                            onRespnse!!.onBadRequest(flag, response.errorBody()!!.string())

                        } catch (e: IOException) {
                            e.printStackTrace()
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<T?>, t: Throwable) {

                if (ShowDialog) progressDialog.dismiss()
                HelpMe.getInstance(context)!!.retrofironFailure(t)
            }
        })
    }

    companion object {
        /**
         * Created by lenovo on 6/28/2017.
         */
        val TAG = HandelCalls::class.java.simpleName
        private var context: Context? = null
        private var instance: HandelCalls? = null
        private var restRetrofit: RestRetrofit? = null
        //private HandleNoContent onNoContent;
        /**
         * @param context create ana object if it's not already created (singleton)
         * @return reference to that class
         */
        fun getInstance(context: Context?): HandelCalls? {
            Companion.context = context
            if (instance == null) {
                instance = HandelCalls()
                restRetrofit = RestRetrofit.getInstance(context)
            }
            return instance
        }
    }
}