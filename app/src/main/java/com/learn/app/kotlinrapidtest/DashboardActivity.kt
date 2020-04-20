package com.learn.app.kotlinrapidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val context = this

        menu_rs.setOnClickListener {
            val intent = Intent(context, MenuRumahSakit::class.java)
            startActivity(intent)
        }

        menu_antrian.setOnClickListener {
            val intent = Intent(context, MenuAntrian::class.java)
            startActivity(intent)
        }

        menu_booking.setOnClickListener {
            val intent = Intent(context, MenuBooking::class.java)
            startActivity(intent)
        }

        menu_hasil.setOnClickListener {
            val intent = Intent(context, MenuHasil::class.java)
            startActivity(intent)
        }

    }
}
