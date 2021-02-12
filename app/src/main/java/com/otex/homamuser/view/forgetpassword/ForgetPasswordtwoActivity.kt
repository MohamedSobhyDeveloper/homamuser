package com.otex.homamuser.view.forgetpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityForgetPasswordoneBinding
import com.otex.homamuser.databinding.ActivityForgetPasswordtwoBinding

class ForgetPasswordtwoActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgetPasswordtwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityForgetPasswordtwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}