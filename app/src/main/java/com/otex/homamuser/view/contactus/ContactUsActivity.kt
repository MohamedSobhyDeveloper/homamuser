package com.otex.homamuser.view.contactus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityContactUsBinding
import com.otex.homamuser.databinding.ActivityLoginBinding
import com.otex.homamuser.view.home.HomeActivity
import com.otex.homamuser.view.login.LoginActivityViewModel

class ContactUsActivity : AppCompatActivity() {


    private var contactUsViewModel: ContactUsViewModel? = null
    lateinit var binding: ActivityContactUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        click()
    }
    private fun click() {
        binding.backbtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }    private fun initialize() {
        contactUsViewModel = ViewModelProvider(this).get(ContactUsViewModel::class.java)
    }
}
