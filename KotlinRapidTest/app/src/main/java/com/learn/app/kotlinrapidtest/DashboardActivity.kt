package com.learn.app.kotlinrapidtest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.json.JSONObject

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

        menu_profile.setOnClickListener {
            val intent = Intent(context, ProfileActivity::class.java)
            startActivity(intent)
        }

        val sharedPref = getSharedPreferences("PROFIL", Context.MODE_PRIVATE)
        val dtusername = sharedPref?.getString("username", "").toString()

        getUsers(dtusername)

        val user = getSharedPreferences("PASIEN", Context.MODE_PRIVATE)
        val nama = user?.getString("nama", "").toString()

        txt_user.text = nama

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
