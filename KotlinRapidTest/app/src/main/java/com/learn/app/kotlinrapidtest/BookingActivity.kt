package com.learn.app.kotlinrapidtest

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_booking.*
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_menu_booking.*
import kotlinx.android.synthetic.main.activity_menu_booking.kembali_booking
import org.json.JSONArray
import org.json.JSONObject

class BookingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val context = this

        kembali_booking_activity.setOnClickListener {
            val intent = Intent(context, MenuBooking::class.java)
            startActivity(intent)
        }

        var nama = intent.getStringExtra("nama_rs")

        var book_rs: TextView = findViewById(R.id.book_nama_rs)

        book_rs.text = nama.toString()

        val sharedPref = getSharedPreferences("PROFIL", Context.MODE_PRIVATE)
        val dtusername = sharedPref?.getString("username", "").toString()

        getUsers(dtusername)

        val user = getSharedPreferences("PASIEN", Context.MODE_PRIVATE)
        val name = user?.getString("nama", "").toString()

        book_nama.text = name

        btn_booking.setOnClickListener {
            var input_nama_rs: String = book_nama_rs.text.toString()
            var input_nama_pasien: String = book_nama.text.toString()
            var input_jadwal: String = book_jadwal.text.toString()
            var input_keluhan: String = book_keluhan.text.toString()

            inputbooking(input_nama_pasien, input_nama_rs, input_jadwal, input_keluhan)

            val builder = AlertDialog.Builder(this)
            builder.setMessage("Booking Success")
            builder.setPositiveButton("OK", { dialogInterface: DialogInterface, i: Int ->
                val intent = Intent(context, DashboardActivity::class.java)
                startActivity(intent)
            })
            builder.show()
        }

    }

    fun inputbooking(data1: String, data2: String, data3: String, data4: String) {
        AndroidNetworking.post("http://192.168.0.7/api-rapidtest/antrian/create.antrian.php")
            .addBodyParameter("nama_pasien", data1)
            .addBodyParameter("nama_rs", data2)
            .addBodyParameter("jadwalpilihan", data3)
            .addBodyParameter("keluhan", data4)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {

                }

                override fun onError(anError: ANError?) {

                }
            })
    }

    fun getUsers(data: String) {
        AndroidNetworking.post("http://192.168.0.7/api-rapidtest/pasien/read.pasien.php")
            .addBodyParameter("username", data)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("nama_user"))
                        val nama: String = jsonObject.optString("nama_user")
                        val alamat: String = jsonObject.optString("alamat")
                        val notelp: String = jsonObject.optString("nohp_user")
                        val umur: String = jsonObject.optString("umur")
                        val jekel: String = jsonObject.optString("jeniskelamin")
                        val goldar: String = jsonObject.optString("goldarah")
                        val ttl: String = jsonObject.optString("ttl_pasien")

                        val pasien = getSharedPreferences("PASIEN", Context.MODE_PRIVATE)
                        val edit = pasien.edit()
                        edit.putString("nama", "$nama")
                        edit.putString("alamat", "$alamat")
                        edit.putString("notelp", "$notelp")
                        edit.putString("umur", "$umur")
                        edit.putString("jekel", "$jekel")
                        edit.putString("goldar", "$goldar")
                        edit.putString("ttl", "$ttl")
                        edit.apply()
                    }
                }

                override fun onError(anError: ANError?) {

                }
            })

    }

}
