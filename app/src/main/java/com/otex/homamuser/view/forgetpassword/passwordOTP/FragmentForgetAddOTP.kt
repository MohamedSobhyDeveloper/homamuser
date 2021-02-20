package com.otex.homamuser.view.forgetpassword.passwordOTP

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.otex.homamuser.R
import com.otex.homamuser.databinding.FragmentForgetAddOTFragmentBinding
import com.otex.homamuser.view.login.LoginActivity


class FragmentForgetAddOTP : Fragment() {

    companion object {
        fun newInstance() = FragmentForgetAddOTP()
    }

    private lateinit var viewModel: FragmentForgetAddOTViewModel
    lateinit var binding: FragmentForgetAddOTFragmentBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgetAddOTFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentForgetAddOTViewModel::class.java)
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
        binding.editOtp.setOtpCompletionListener {
            Toast.makeText(context,"done",Toast.LENGTH_LONG).show()
        }
         navController.navigate(R.id.action_fragmentForgetAddOTP_to_fragmentUpdatePassword)
    }

    private fun initialize(view: View) {
        binding.editOtp.setOtpCompletionListener {
            Toast.makeText(context,"done",Toast.LENGTH_LONG).show()
        }
        navController = Navigation.findNavController(view)

    }

}