package com.otex.homamuser.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityVerificationCodeBinding
import com.otex.homamuser.view.login.LoginActivity
import es.dmoral.toasty.Toasty
import java.util.HashMap

class VerificationCodeActivity : AppCompatActivity() {
    lateinit var binding: ActivityVerificationCodeBinding
    private var registerActivityViewModel : RegisterActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        click()
    }

    private fun click() {
        binding.backbtn.setOnClickListener {
            finish()
        }


        binding.btnVerify.setOnClickListener {
            if (binding.editOtp.text.toString().isNotEmpty()){
                val map = HashMap<String, String?>()
                map["phone"] = intent.getStringExtra("phone")
                map["code"] = binding.editOtp.text.toString()
                registerActivityViewModel?.activePhone(this,map)
            }else{
                Toasty.info(this,getString(R.string.enter_verification_code))
            }
        }
    }

    private fun initialize() {
        binding.txtPhoneView.text=intent.getStringExtra("phone")
        registerActivityViewModel = ViewModelProvider(this).get(RegisterActivityViewModel::class.java)

        registerActivityViewModel!!.activeLivedata.observe(this) {

            Toasty.success(this,getString(R.string.phone_verified))
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

        }

    }
}