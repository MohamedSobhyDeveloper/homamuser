package com.otex.homamuser.view.forgetpassword.updatepass

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.otex.homamuser.R

class FragmentUpdatePassword : Fragment() {

    companion object {
        fun newInstance() = FragmentUpdatePassword()
    }

    private lateinit var viewModel: FragmentUpdatePasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_update_password_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentUpdatePasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}