package com.otex.homamuser.view.editprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
        binding.btnSave.setOnClickListener {
            username=binding.editUsername.text.toString()
            email_or_phone=binding.editEmailPhone.text.toString()
            phone=binding.editPhone.text.toString()
            password=binding.editPassword.text.toString()
            confirm_pass=binding.editPasswordConfirm.text.toString()

            if(username.equals("")){
                binding.editUsername.setError(getString(R.string.enter_username))
            }else if(email_or_phone.equals("")){
                binding.editEmailPhone.setError(getString(R.string.enter_email_phone))
            }else if(phone.equals("")){
                binding.editPhone.setError(getString(R.string.enter_phone))

            }else if( password.equals("")){
                binding.editPassword.setError(getString(R.string.enter_password))

            }else if(confirm_pass.equals("")){
                binding.editPasswordConfirm.setError(getString(R.string.enter_retype_pass))

            }else{
                if(confirm_pass==password){
                    update_profile()
                }else{
                    binding.editPasswordConfirm.setError(getString(R.string.dosent_match))
                }
            }

        }

    }

    private fun update_profile() {

        Toast.makeText(this,"Done!",Toast.LENGTH_LONG).show()
    }

    private fun initialize() {
        loginviewmodel = ViewModelProvider(this).get(EditProfileViewModel::class.java)
    }

}