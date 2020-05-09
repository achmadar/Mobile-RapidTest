package com.learn.app.kotlinrapidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_profile_rumah_sakit.*
import kotlinx.android.synthetic.main.list_rumah_sakit.*
import org.json.JSONArray
import org.json.JSONObject

class ProfileRumahSakit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_rumah_sakit)

        val context = this

        kembali_profileRS.setOnClickListener {
            val intent = Intent(context, MenuRumahSakit::class.java)
            startActivity(intent)
        }

        val nama = intent.getStringExtra("nama_rs")
        val nama_rs:TextView = findViewById(R.id.prf_namaRS)
        nama_rs.text =nama.toString()

        val notelp = intent.getStringExtra("notelp_rs")
        val notelp_rs:TextView = findViewById(R.id.prf_telpRS)
        notelp_rs.text =notelp.toString()

        val jadwal = intent.getStringExtra("jadwal_rs")
        val jadwal_rs:TextView = findViewById(R.id.prf_jadwalRS)
        jadwal_rs.text =jadwal.toString()

        val alamat = intent.getStringExtra("alamat_rs")
        val alamat_rs:TextView = findViewById(R.id.prf_alamatRS)
        alamat_rs.text =alamat.toString()

    }

}
