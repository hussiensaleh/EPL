package com.saleh.mol5satak

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_admin_log_in.*

class AdminLogIn : AppCompatActivity() {
    var username=""
    var password=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_log_in)

        login_button.setOnClickListener {
            username=username_ET.text.toString()
            password=password_ET.text.toString()
            if (username=="Admin"&&password=="Admin"){
                var i=Intent(this,AdminMainActivity::class.java)
                startActivity(i)
            }
        }
    }
}
