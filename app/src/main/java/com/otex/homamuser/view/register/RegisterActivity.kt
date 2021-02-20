package com.otex.homamuser.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityRegisterBinding
import com.otex.homamuser.view.home.HomeActivity
import com.otex.homamuser.view.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private var registerActivityViewModel : RegisterActivityViewModel? = null
    lateinit var binding: ActivityRegisterBinding
    var email_or_phone:String=""
    var password:String=""
    var confirm_password:String=""
    var phone:String=""
    var username:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
         click()
    }

    private fun initialize() {
        registerActivityViewModel = ViewModelProvider(this).get(RegisterActivityViewModel::class.java)
    }

    private fun click() {

        binding.linRegister.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.btnRegiste.setOnClickListener {
            username=binding.editUsername.text.toString()
            email_or_phone=binding.editEmailPhone.text.toString()
            phone=binding.editPhone.text.toString()
            password=binding.editPassword.text.toString()
            confirm_password=binding.editPasswordConfirm.text.toString()
            if(username.equals("")){
                binding.editUsername.setError(getString(R.string.enter_username))
            }else if(email_or_phone.equals("")){
                binding.editEmailPhone.setError(getString(R.string.enter_email_phone))
            }else if(phone.equals("")){
                binding.editPhone.setError(getString(R.string.enter_phone))
            }else if(password.equals("")){
                binding.editPassword.setError(getString(R.string.enter_password))
            }else if(confirm_password.equals("")){
                binding.editPasswordConfirm.setError(getString(R.string.enter_password))
            }else{
                if(confirm_password==password){
                    register(username,email_or_phone,phone,password,confirm_password)
                }else{
                    binding.editPasswordConfirm.setError(getString(R.string.confirm_not_equal_password))
                }
            }

        }

        binding.backbtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun register(username: String, emailOrPhone: String, phone: String, password: String, confirmPassword: String) {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}