package com.otex.homamuser.view.forgetpassword.fragmentforgetpass

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

class FragmentForgetPasswordAddEmail : Fragment() {

    companion object {
        fun newInstance() = FragmentForgetPasswordAddEmail()
    }

    private lateinit var viewModel: FragmentForgetPasswordAddEmailViewModel
    lateinit var binding: FragmentForgetPasswordAddEmailFragmentBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_forget_password_add_email_fragment,
            container,
            false
        )

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentForgetPasswordAddEmailViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(view)
    }

    private fun initialize(view: View) {

            navController = Navigation.findNavController(view)

    }

}