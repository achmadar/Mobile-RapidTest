package com.learn.app.kotlinrapidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val context = this

        btn_login.setOnClickListener {

            val intent = Intent(context, DashboardActivity::class.java)

            startActivity(intent)
        }

        btn_register.setOnClickListener {

            val intent = Intent(context, RegisterActivity::class.java)

            startActivity(intent)
        }

    }
}
