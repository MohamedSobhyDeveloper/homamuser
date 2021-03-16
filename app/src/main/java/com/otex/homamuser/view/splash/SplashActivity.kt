package com.otex.homamuser.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityLoginBinding
import com.otex.homamuser.databinding.ActivitySplashBinding
import com.otex.homamuser.utlitites.UserInfo
import com.otex.homamuser.view.MainActivity
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.home.HomeActivity
import com.otex.homamuser.view.login.LoginActivity

class SplashActivity : BaseActivity() {

    lateinit var binding: ActivitySplashBinding
    var signstate:Boolean=false
    var userInfo:UserInfo?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
             userInfo=UserInfo(this)
                signstate=userInfo!!.getUserSignState()
                 if(signstate==true) {
                     startActivity(Intent(this, HomeActivity::class.java))
                     finish()
                 }else{
                     startActivity(Intent(this, LoginActivity::class.java))
                     finish()
                 }

        }, 2000)

    }
}