package com.otex.homamuser.view.myprofile

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.otex.homamuser.databinding.ActivityMyProfileBinding
import com.otex.homamuser.utlitites.Constant
import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.editprofile.EditProfileActivity
import com.otex.homamuser.view.editprofile.EditProfileViewModel
import com.otex.homamuser.view.home.HomeActivity
import com.otex.homamuser.view.login.LoginActivity
import com.otex.homamuser.view.myorder.MyOrderListActivity

class MyProfileActivity : BaseActivity() {
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
            finish()
        }
        binding.myorders.setOnClickListener {
            startActivity(Intent(this, MyOrderListActivity::class.java))
        }
        binding.btnEdit.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
        }
        binding.logout.setOnClickListener {
            PrefsUtil.with(this).add(Constant.token,"").apply()
            val intent =Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
             startActivity(intent)
        }
    }

    private fun initialize() {
        binding.username.text= PrefsUtil.with(this)[Constant.username, ""].toString()
        binding.useremail.text= PrefsUtil.with(this)[Constant.email, ""].toString()

        loginviewmodel = ViewModelProvider(this).get(EditProfileViewModel::class.java)
    }
}