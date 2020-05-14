package com.learn.app.kotlinrapidtest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_profile.*
import org.json.JSONObject

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val context = this

        back_profile.setOnClickListener {
            val back = Intent(context, DashboardActivity::class.java)
            startActivity(back)
        }

        btn_edit_profile.setOnClickListener {
            val edit = Intent(context, EditProfile::class.java)
            startActivity(edit)
        }

        btn_logout.setOnClickListener {
            val sharedPreferences = getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putString("STATUS", "0")
            editor.apply()

            startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
            finish()
        }

        val sharedPref = getSharedPreferences("PROFIL", Context.MODE_PRIVATE)
        val dtusername = sharedPref.getString("email", "").toString()

        getUsers(dtusername)

        val pasien = getSharedPreferences("PASIEN", Context.MODE_PRIVATE)
        val nama = pasien?.getString("nama", "").toString()
        val alamat = pasien?.getString("alamat", "").toString()
        val nohp = pasien?.getString("notelp", "").toString()
        val umur = pasien?.getString("umur", "").toString()
        val jekel = pasien?.getString("jekel", "").toString()
        val goldar = pasien?.getString("goldar", "").toString()
        val ttl = pasien?.getString("ttl", "").toString()

        profile_nama.text = nama
        profile_alamat.text = alamat
        profile_notelp.text = nohp
        profile_umur.text = umur
        profile_jekel.text = jekel
        profile_goldar.text = goldar
        profile_ttl.text = ttl

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
