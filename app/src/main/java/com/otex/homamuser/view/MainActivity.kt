package com.otex.homamuser.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.otex.homamuser.R
import com.otex.homamuser.view.baseActivity.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}