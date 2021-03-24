package com.otex.homamuser.view.editprofile

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityEditProfileBinding
import com.otex.homamuser.utlitites.Constant
import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.myprofile.MyProfileActivity
import java.util.HashMap

class EditProfileActivity : BaseActivity() {
    private var updateProfileviewmodel : EditProfileViewModel? = null
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

            assimentVariable()

            if(username.equals("")){
                binding.editUsername.setError(getString(R.string.enter_username))
            }else if(email_or_phone.equals("")){
                binding.editEmailPhone.setError(getString(R.string.enter_email_phone))
            }else if(phone.equals("")){
                binding.editPhone.setError(getString(R.string.enter_phone))

            }else{
                    update_profile(username,email_or_phone,phone)

            }

        }

    }

    private fun assimentVariable() {
        username=binding.editUsername.text.toString()
        email_or_phone=binding.editEmailPhone.text.toString()
        phone=binding.editPhone.text.toString()
        password=binding.editPassword.text.toString()
        confirm_pass=binding.editPasswordConfirm.text.toString()
    }

    private fun update_profile(
        username: String,
        emailOrPhone: String,
        phone: String
    ) {

        val map = HashMap<String, String?>()
        map.put("email",emailOrPhone)
        map.put("phone",phone)
        map.put("name",username)
        updateProfileviewmodel!!.updateProfile(this, map)



    }

    private fun initialize() {
        binding.editUsername.setText(PrefsUtil.with(this)[Constant.username, ""]
            , TextView.BufferType.EDITABLE)
        binding.editEmailPhone.setText(PrefsUtil.with(this)[Constant.email, ""],
            TextView.BufferType.EDITABLE)

        updateProfileviewmodel = ViewModelProvider(this).get(EditProfileViewModel::class.java)
        updateProfileviewmodel!!.updateProfileLiveData.observe(this) {

            Toast.makeText(this,it.message+"",Toast.LENGTH_SHORT).show()

             finish()

        }
    }

}