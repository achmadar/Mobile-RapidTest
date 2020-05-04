package com.learn.app.kotlinrapidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_menu_hasil.*
import kotlinx.android.synthetic.main.activity_menu_rumah_sakit.*

class MenuHasil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_hasil)

        val context = this

        kembali_hasil.setOnClickListener {
            val kembali = Intent(context, DashboardActivity::class.java)
            startActivity(kembali)
        }

        var nama = intent.getStringExtra("nama_pasien")
        var rumahsakit = intent.getStringExtra("nama_rs")
        var jadwal = intent.getStringExtra("jadwalpilihan")
        var nama_pasien: TextView = findViewById(R.id.hasil_nama)
        var nama_rs: TextView = findViewById(R.id.hasil_rs)
        var jadwal_rs: TextView = findViewById(R.id.hasil_jadwal)
        nama_pasien.text = nama.toString()
        nama_rs.text = rumahsakit.toString()
        jadwal_rs.text = jadwal.toString()

    }
}
