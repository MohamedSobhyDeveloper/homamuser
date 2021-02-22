package com.otex.homamuser.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityLoginBinding
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.forgetpassword.ActivityForgetPassword
import com.otex.homamuser.view.home.HomeActivity
import com.otex.homamuser.view.register.RegisterActivity

class LoginActivity : BaseActivity() {

    private var loginviewmodel : LoginActivityViewModel? = null
    lateinit var binding: ActivityLoginBinding
    var email_or_phone:String=""
    var password:String=""
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

        binding.btnLogin.setOnClickListener {
            email_or_phone=binding.editEmailPhone.text.toString()
            password=binding.editPassword.text.toString()
            if(email_or_phone.equals("")){
                binding.editEmailPhone.setError(getString(R.string.enter_email_phone))
            }else if(password.equals("")){
                binding.editPassword.setError(getString(R.string.enter_password))
            }else{

                login(email_or_phone,password)
            }
        }
    }

    private fun login(emailOrPhone: String, password: String) {

        startActivity(Intent(this, HomeActivity::class.java))
        finish()

    }

    private fun initialize() {
        loginviewmodel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
    }


}