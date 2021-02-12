package com.otex.homamuser.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.otex.homamuser.databinding.ActivityRegisterBinding
import com.otex.homamuser.view.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private var registerActivityViewModel : RegisterActivityViewModel? = null
    lateinit var binding: ActivityRegisterBinding
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

        binding.backbtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}