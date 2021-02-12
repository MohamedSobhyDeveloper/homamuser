package com.otex.homamuser.view.forgetpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityForgetPasswordoneBinding
import com.otex.homamuser.databinding.ActivityLoginBinding

class ForgetPasswordoneActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgetPasswordoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}