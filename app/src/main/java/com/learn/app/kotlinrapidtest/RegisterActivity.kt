package com.learn.app.kotlinrapidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val context = this

        btn_daftar.setOnClickListener {
            val intent = Intent(context, DashboardActivity::class.java)
            startActivity(intent)
        }

    }
}
