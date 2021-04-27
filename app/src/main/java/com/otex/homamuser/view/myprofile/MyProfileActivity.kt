package com.otex.homamuser.view.myprofile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
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
    private var myProfileViewModel : MyProfileViewModel? = null
    lateinit var binding: ActivityMyProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        getMyProfile()
        click()

    }

    private fun getMyProfile() {

        myProfileViewModel!!.myProfile(this, null)

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

        myProfileViewModel = ViewModelProvider(this).get(MyProfileViewModel::class.java)
        myProfileViewModel!!.myProfileLiveData.observe(this) {


            binding.username.text= it.user.name
//            binding.useremail.text= it.user.email

            if(it.user.phone!=null){
                binding.userPhone.text= it.user.phone
                binding.userPhone.visibility= View.VISIBLE

            }else{
                binding.userPhone.visibility= View.GONE
            }

        }

        }
}