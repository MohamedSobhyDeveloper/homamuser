package com.otex.homamuser.view.editprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityEditProfileBinding
import com.otex.homamuser.databinding.ActivityLoginBinding
import com.otex.homamuser.view.home.HomeActivity
import com.otex.homamuser.view.login.LoginActivity
import com.otex.homamuser.view.login.LoginActivityViewModel
import com.otex.homamuser.view.myprofile.MyProfileActivity

class EditProfileActivity : AppCompatActivity() {
    private var loginviewmodel : EditProfileViewModel? = null
    lateinit var binding: ActivityEditProfileBinding

    var username:String=""
    var email_or_phone:String=""
    var phone:String=""
    var password:String=""
    var confirm_pass:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()

        click()

    }

    private fun click() {

        binding.backbtn.setOnClickListener {
            startActivity(Intent(this, MyProfileActivity::class.java))
            finish()
        }


    }

    private fun initialize() {
        loginviewmodel = ViewModelProvider(this).get(EditProfileViewModel::class.java)
    }

}