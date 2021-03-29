package com.otex.homamuser.view.contactus

import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.otex.homamuser.R
import com.otex.homamuser.databinding.ActivityContactUsBinding
import com.otex.homamuser.utlitites.Constant
import com.otex.homamuser.view.baseActivity.BaseActivity
import es.dmoral.toasty.Toasty


class ContactUsActivity : BaseActivity() {


    private var contactUsViewModel: ContactUsViewModel? = null
    lateinit var binding: ActivityContactUsBinding
     var username:String=""
     var email:String=""
     var subject:String=""
     var message:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        click()
        txt_watcher()

    }

    private fun txt_watcher() {
        binding.editMessage.addTextChangedListener(object : android.text.TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    binding.txtLength.text= s.length.toString()+"/100"
                }
            }
        })
    }

    private fun click() {
        binding.backbtn.setOnClickListener {
            finish()
        }
        binding.btnNext.setOnClickListener {
            username=binding.editUsername.text.toString()
            email=binding.editPhone.text.toString()
            message=binding.editMessage.text.toString()
            subject=binding.editSubject.text.toString()

            if(username.equals("")){
                binding.editUsername.setError(getString(R.string.enter_username))
            }else if(email.equals("")){
                binding.editPhone.setError(getString(R.string.enter_phone))
            }else if(subject.equals("")){
                binding.editSubject.setError(getString(R.string.enter_subject))
            }else if(message.equals("")){
                binding.editMessage.setError(getString(R.string.enter_message))
            }else{
                contact(username,email,message,subject)
            }

        }

    }

    private fun contact(username: String, email: String, message: String, subject: String) {
        val map = HashMap<String, String?>()
        map.put("email",email)
        map.put("subject",subject)
        map.put("message",message)
        map.put("name",username)

        contactUsViewModel?.contactUs(this,map)
    }

    private fun initialize() {
        contactUsViewModel = ViewModelProvider(this).get(ContactUsViewModel::class.java)

        contactUsViewModel!!.contactsLiveData.observe(this) {


            Toasty.success(this, it.message, Toast.LENGTH_SHORT, true).show()
            finish()



        }
    }
}
