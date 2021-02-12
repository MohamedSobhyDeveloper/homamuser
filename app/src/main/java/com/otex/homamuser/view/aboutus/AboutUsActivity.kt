package com.otex.homamuser.view.aboutus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityAboutUsBinding
import com.otex.homamuser.databinding.ActivityLoginBinding
import com.otex.homamuser.view.login.LoginActivityViewModel

class AboutUsActivity : AppCompatActivity() {
    private var aboutUsViewModel : AboutUsViewModel? = null
    lateinit var binding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()

    }
    private fun initialize() {
        aboutUsViewModel = ViewModelProvider(this).get(AboutUsViewModel::class.java)
    }
}