package com.otex.homamuser.view.forgetpassword.fragmentforgetpass

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityLoginBinding
import com.otex.homamuser.databinding.FragmentForgetPasswordAddEmailFragmentBinding
import com.otex.homamuser.view.login.LoginActivity

class FragmentForgetPasswordAddEmail : Fragment() {

    companion object {
        fun newInstance() = FragmentForgetPasswordAddEmail()
    }

    private lateinit var viewModel: FragmentForgetPasswordAddEmailViewModel
    lateinit var binding: FragmentForgetPasswordAddEmailFragmentBinding
    private lateinit var navController: NavController
   var emai:String=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgetPasswordAddEmailFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentForgetPasswordAddEmailViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(view)
        click()
    }

    private fun click() {
        binding.btnNext.setOnClickListener {
            emai=binding.editEmail.text.toString()
            if(emai.equals("")){
                binding.editEmail.setError(getString(R.string.enter_email))
            }else{
                addEmail(emai)
            }
        }
        binding.backbtn.setOnClickListener {
            startActivity(Intent(context, LoginActivity::class.java))
        }

    }

    private fun addEmail(emai: String) {

        navController.navigate(R.id.action_fragmentForgetPasswordAddEmail_to_fragmentForgetAddOTP)
    }

    private fun initialize(view: View) {

            navController = Navigation.findNavController(view)

    }

}