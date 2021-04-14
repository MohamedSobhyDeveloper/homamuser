package com.otex.homamuser.view.forgetpassword.passwordOTP

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.otex.homamuser.R
import com.otex.homamuser.databinding.FragmentForgetAddOTFragmentBinding
import com.otex.homamuser.view.forgetpassword.fragmentforgetpass.FragmentForgetPasswordAddEmailViewModel
import com.otex.homamuser.view.login.LoginActivity
import java.util.HashMap


class FragmentForgetAddOTP : Fragment() {

    companion object {
        fun newInstance() = FragmentForgetAddOTP()
    }

    private lateinit var viewModel: FragmentForgetPasswordAddEmailViewModel
    lateinit var binding: FragmentForgetAddOTFragmentBinding
    private lateinit var navController: NavController
    private var code:String=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgetAddOTFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

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

        binding.btnNext.setOnClickListener {
            addOTP()
        }

        binding.backbtn.setOnClickListener {
            navController.navigate(R.id.action_fragmentForgetAddOTP_to_fragmentForgetPasswordAddEmail)
        }
    }

    private fun addOTP() {
         code=binding.editOtp.text.toString()
        if(code==""){
            Toast.makeText(activity,getString(R.string.enter_code),Toast.LENGTH_SHORT).show()
        }else{
            sendCodeToReset(code)
        }
         navController.navigate(R.id.action_fragmentForgetAddOTP_to_fragmentUpdatePassword)
    }

    private fun sendCodeToReset(code: String) {
        val map = HashMap<String, String?>()
        map.put("phone","phone")
        map.put("code",code)
        activity?.let { viewModel!!.resetPass(it,map) }
        navController.navigate(R.id.action_fragmentForgetAddOTP_to_fragmentUpdatePassword)
    }

    private fun initialize(view: View) {

        navController = Navigation.findNavController(view)
        viewModel = ViewModelProvider(this).get(FragmentForgetPasswordAddEmailViewModel::class.java)
        this!!.activity?.let {
            viewModel!!.resetpassLivedata.observe(it) {

                navController.navigate(R.id.action_fragmentForgetAddOTP_to_fragmentUpdatePassword)

            }
        }
    }

}