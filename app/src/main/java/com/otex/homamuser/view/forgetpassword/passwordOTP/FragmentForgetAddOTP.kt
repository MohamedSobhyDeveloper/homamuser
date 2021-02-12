package com.otex.homamuser.view.forgetpassword.passwordOTP

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.otex.homamuser.R

class FragmentForgetAddOTP : Fragment() {

    companion object {
        fun newInstance() = FragmentForgetAddOTP()
    }

    private lateinit var viewModel: FragmentForgetAddOTViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forget_add_o_t_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentForgetAddOTViewModel::class.java)
        // TODO: Use the ViewModel
    }

}