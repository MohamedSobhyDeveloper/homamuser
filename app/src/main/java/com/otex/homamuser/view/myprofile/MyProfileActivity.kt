package com.otex.homamuser.view.myprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityEditProfileBinding
import com.otex.homamuser.databinding.ActivityMyProfileBinding
import com.otex.homamuser.view.editprofile.EditProfileActivity
import com.otex.homamuser.view.editprofile.EditProfileViewModel
import com.otex.homamuser.view.home.HomeActivity
import com.otex.homamuser.view.login.LoginActivity

class MyProfileActivity : AppCompatActivity() {
    private var loginviewmodel : EditProfileViewModel? = null
    lateinit var binding: ActivityMyProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()

        click()

    }

    private fun click() {
        binding.backbtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        binding.btnEdit.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
            finish()
        }
        binding.logout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun initialize() {
        loginviewmodel = ViewModelProvider(this).get(EditProfileViewModel::class.java)
    }
}