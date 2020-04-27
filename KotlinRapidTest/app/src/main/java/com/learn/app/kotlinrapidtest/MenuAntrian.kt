package com.learn.app.kotlinrapidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu_antrian.*
import kotlinx.android.synthetic.main.activity_menu_rumah_sakit.*

class MenuAntrian : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_antrian)

        val context = this

        kembali_antrian.setOnClickListener {
            val kembali = Intent(context, DashboardActivity::class.java)
            startActivity(kembali)
        }

    }
}
