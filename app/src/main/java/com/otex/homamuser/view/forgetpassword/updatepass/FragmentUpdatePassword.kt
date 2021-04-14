package com.otex.homamuser.view.forgetpassword.updatepass

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.otex.homamuser.R
import com.otex.homamuser.databinding.FragmentForgetPasswordAddEmailFragmentBinding
import com.otex.homamuser.databinding.FragmentUpdatePasswordFragmentBinding
import com.otex.homamuser.view.forgetpassword.ActivityForgetPassword
import com.otex.homamuser.view.forgetpassword.fragmentforgetpass.FragmentForgetPasswordAddEmailViewModel
import com.otex.homamuser.view.login.LoginActivity
import java.util.HashMap

class FragmentUpdatePassword : Fragment() {

    companion object {
        fun newInstance() = FragmentUpdatePassword()
    }

    private lateinit var viewModel: FragmentForgetPasswordAddEmailViewModel
    lateinit var binding: FragmentUpdatePasswordFragmentBinding
    private lateinit var navController: NavController
    var newpassword:String=""
    var retypepass:String=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdatePasswordFragmentBinding.inflate(inflater, container, false)

        return binding.root    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentForgetPasswordAddEmailViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(view)
        click()
    }

    private fun click() {
        binding.backbtn.setOnClickListener {
            navController.navigate(R.id.action_fragmentUpdatePassword_to_fragmentForgetAddOTP)
        }


        binding.btnUpdate.setOnClickListener {
            newpassword=binding.editNewPassword.text.toString()
            retypepass=binding.editReTypePassword.text.toString()
            if(newpassword.equals("")){
                binding.editNewPassword.setError(getString(R.string.enter_new_pass))
            }else if(retypepass.equals("")){
                binding.editReTypePassword.setError(getString(R.string.enter_retype_pass))
            }else{
                if(retypepass==newpassword){
                    updatepassword(newpassword,retypepass)
                }else{
                    binding.editReTypePassword.setError(getString(R.string.dosent_match))
                }
            }
        }
    }

    private fun updatepassword(newpassword: String, retypepass: String) {
        val map = HashMap<String, String?>()
        map.put("password",newpassword)
        map.put("confirm_password",retypepass)
        activity?.let { viewModel!!.updatefPass(it,map) }
        startActivity(Intent(context, LoginActivity::class.java))

    }

    private fun initialize(view: View) {
        navController = Navigation.findNavController(view)

        viewModel = ViewModelProvider(this).get(FragmentForgetPasswordAddEmailViewModel::class.java)
        this!!.activity?.let {
            viewModel!!.updatepassLivedata.observe(it) {

                startActivity(Intent(context, LoginActivity::class.java))

            }
        }

    }
}