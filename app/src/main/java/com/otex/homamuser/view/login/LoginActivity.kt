package com.otex.homamuser.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.otex.homamuser.databinding.ActivityLoginBinding
import com.otex.homamuser.view.forgetpassword.ActivityForgetPassword
import com.otex.homamuser.view.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private var loginviewmodel : LoginActivityViewModel? = null
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()

        click()

    }

    private fun click() {

        binding.linRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
             finish()
        }

        binding.backbtn.setOnClickListener {
            finish()
        }

        binding.txtForgoPassword.setOnClickListener {
            startActivity(Intent(this, ActivityForgetPassword::class.java))

        }
    }

    private fun initialize() {
        loginviewmodel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
    }


}