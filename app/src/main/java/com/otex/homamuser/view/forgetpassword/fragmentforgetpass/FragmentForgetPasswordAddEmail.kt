package com.otex.homamuser.view.forgetpassword.fragmentforgetpass

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.otex.homamuser.R

class FragmentForgetPasswordAddEmail : Fragment() {

    companion object {
        fun newInstance() = FragmentForgetPasswordAddEmail()
    }

    private lateinit var viewModel: FragmentForgetPasswordAddEmailViewModel

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
        // TODO: Use the ViewModel
    }

}