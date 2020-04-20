package com.learn.app.kotlinrapidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this

        btn_start.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)

            startActivity(intent)
        }
    }
}
