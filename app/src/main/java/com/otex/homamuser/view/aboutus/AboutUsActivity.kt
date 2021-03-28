package com.otex.homamuser.view.aboutus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityAboutUsBinding
import com.otex.homamuser.databinding.ActivityLoginBinding
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.home.HomeActivity
import com.otex.homamuser.view.login.LoginActivityViewModel

class AboutUsActivity : BaseActivity() {
    private var aboutUsViewModel : AboutUsViewModel? = null
    lateinit var binding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()

        click()
    }
    private fun click() {
        binding.backbtn.setOnClickListener {
            finish()
        }
    }
    private fun initialize() {
        aboutUsViewModel = ViewModelProvider(this).get(AboutUsViewModel::class.java)
    }
}