package com.otex.homamuser.view.forgetpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityForgetPasswordthreeBinding
import com.otex.homamuser.databinding.ActivityForgetPasswordtwoBinding

class ForgetPasswordthreeActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgetPasswordthreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityForgetPasswordthreeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}