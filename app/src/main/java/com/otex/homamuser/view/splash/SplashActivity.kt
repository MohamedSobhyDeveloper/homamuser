package com.otex.homamuser.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityLoginBinding
import com.otex.homamuser.databinding.ActivitySplashBinding
import com.otex.homamuser.view.MainActivity
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.login.LoginActivity

class SplashActivity : BaseActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({

                startActivity(Intent(this, LoginActivity::class.java))
                finish()

        }, 2000)

    }
}