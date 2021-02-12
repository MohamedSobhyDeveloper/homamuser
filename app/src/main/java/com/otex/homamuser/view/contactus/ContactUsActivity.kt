package com.otex.homamuser.view.contactus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityContactUsBinding
import com.otex.homamuser.databinding.ActivityLoginBinding
import com.otex.homamuser.view.login.LoginActivityViewModel

class ContactUsActivity : AppCompatActivity() {


    private var contactUsViewModel: ContactUsViewModel? = null
    lateinit var binding: ActivityContactUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }
    private fun initialize() {
        contactUsViewModel = ViewModelProvider(this).get(ContactUsViewModel::class.java)
    }
}
