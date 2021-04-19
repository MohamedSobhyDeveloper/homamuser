package com.otex.homamuser.view.forgetpassword.fragmentforgetpass

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityLoginBinding
import com.otex.homamuser.databinding.FragmentForgetPasswordAddEmailFragmentBinding
import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.home.HomeActivity
import com.otex.homamuser.view.login.LoginActivity
import com.otex.homamuser.view.login.LoginActivityViewModel
import es.dmoral.toasty.Toasty
import java.util.HashMap

class FragmentForgetPasswordAddEmail : Fragment() {

    companion object {
        fun newInstance() = FragmentForgetPasswordAddEmail()
    }

    private lateinit var viewModel: FragmentForgetPasswordAddEmailViewModel
    lateinit var binding: FragmentForgetPasswordAddEmailFragmentBinding
    private lateinit var navController: NavController
   var phone:String=""
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
            phone=binding.editPhone.text.toString()
            if(phone.equals("")){
                binding.editPhone.setError(getString(R.string.enter_phone))
            }else{
                addPhone(phone)
            }
        }
        binding.backbtn.setOnClickListener {
            startActivity(Intent(context, LoginActivity::class.java))
        }

    }

    private fun addPhone(phone: String) {
        val map = HashMap<String, String?>()
        map.put("phone",phone)
        activity?.let { viewModel!!.forgerPass(it, map) }
    }

    private fun initialize(view: View) {
        navController = Navigation.findNavController(view)
        viewModel = ViewModelProvider(this).get(FragmentForgetPasswordAddEmailViewModel::class.java)
        this!!.activity?.let {
            viewModel!!.forgetpassLivedata.observe(it) {
                if (it.status==1){
                    Toasty.success(requireActivity(), getString(R.string.codesend), Toast.LENGTH_SHORT, true).show()
                     PrefsUtil.with(requireActivity()).add("phoneverify",phone).apply()
                    navController.navigate(R.id.action_fragmentForgetPasswordAddEmail_to_fragmentForgetAddOTP)

                }


            }
        }


    }

}