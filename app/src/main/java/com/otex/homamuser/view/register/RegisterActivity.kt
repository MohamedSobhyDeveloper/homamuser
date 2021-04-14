package com.otex.homamuser.view.register

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityRegisterBinding
import com.otex.homamuser.utlitites.UserInfo
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.login.LoginActivity
import java.util.*

class RegisterActivity : BaseActivity() {
    private var registerActivityViewModel : RegisterActivityViewModel? = null
    lateinit var binding: ActivityRegisterBinding
    private  var emailOrPhone:String=""
    private var password:String=""
    private var confirmPassword:String=""
    private  var phone:String=""
    private var username:String=""
    private var userInfo:UserInfo?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
         click()
    }

    private fun initialize() {
        registerActivityViewModel = ViewModelProvider(this).get(RegisterActivityViewModel::class.java)
        registerActivityViewModel!!.registerLivedata.observe(this) {
              val intent =Intent(this,VerificationCodeActivity::class.java)
                intent.putExtra("phone",it.user.phone)
                startActivity(intent)
//               startActivity(Intent(this, LoginActivity::class.java))
//             finish()
//             Toast.makeText(this,it.message+"",Toast.LENGTH_SHORT).show()

        }
    }

    private fun click() {

        binding.linRegister.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.btnRegiste.setOnClickListener {
            makeRegister()
        }

        binding.backbtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun makeRegister() {
        assimentVariable()
        if(username == ""){
            binding.editUsername.error = getString(R.string.enter_username)
        }else if(emailOrPhone == ""){
            binding.editEmailPhone.error = getString(R.string.enter_email_phone)
        }else if(phone == ""){
            binding.editPhone.error = getString(R.string.enter_phone)
        }else if(password == ""){
            binding.editPassword.error = getString(R.string.enter_password)
        }else if(confirmPassword == ""){
            binding.editPasswordConfirm.error = getString(R.string.enter_password)
        }else{
            if(confirmPassword==password){
                register(username,emailOrPhone,password,phone)
            }else{
                binding.editPasswordConfirm.error = getString(R.string.confirm_not_equal_password)
            }
        }
    }

    private fun assimentVariable() {
        username=binding.editUsername.text.toString()
        emailOrPhone=binding.editEmailPhone.text.toString()
        phone=binding.editPhone.text.toString()
        password=binding.editPassword.text.toString()
        confirmPassword=binding.editPasswordConfirm.text.toString()
    }

    private fun register(username: String, emailOrPhone: String, password: String, phone: String) {

        val map = HashMap<String, String?>()
        map["email"] = emailOrPhone
        map["password"] = password
        map["name"] = username
        map["phone"] = phone
        registerActivityViewModel!!.makeRegister(this, map)


    }
}