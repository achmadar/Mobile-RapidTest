package com.learn.app.kotlinrapidtest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_profile.*
import org.json.JSONArray
import org.json.JSONObject

class EditProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val context = this

        back_edit_profile.setOnClickListener {
            val back = Intent(context, ProfileActivity::class.java)
            startActivity(back)
        }

        val sharedPref = getSharedPreferences("PROFIL", Context.MODE_PRIVATE)
        val dtusername = sharedPref.getString("username", "").toString()

        getUsers(dtusername)

        val pasien = getSharedPreferences("PASIEN", Context.MODE_PRIVATE)
        val nama = pasien?.getString("nama", "").toString()
        val alamat = pasien?.getString("alamat", "").toString()
        val nohp = pasien?.getString("notelp", "").toString()
        val umur = pasien?.getString("umur", "").toString()
        val jekel = pasien?.getString("jekel", "").toString()
        val goldar = pasien?.getString("goldar", "").toString()
        val ttl = pasien?.getString("ttl", "").toString()

        edit_nama.setText(nama)
        edit_alamat.setText(alamat)
        edit_no_hp.setText(nohp)
        edit_umur.setText(umur)
        edit_jenis_kelamin.setText(jekel)
        edit_gol_darah.setText(goldar)
        edit_ttl.setText(ttl)

        btn_simpan.setOnClickListener {

                var ednama:String = edit_nama.text.toString()
                var edalamat:String = edit_alamat.text.toString()
                var ednohp:String = edit_no_hp.text.toString()
                var edumur:String = edit_umur.text.toString()
                var edjekel:String = edit_jenis_kelamin.text.toString()
                var edgoldar:String = edit_gol_darah.text.toString()
                var edttl:String = edit_ttl.text.toString()

            updateUser(dtusername, ednama, edalamat, ednohp, edumur, edjekel, edgoldar, edttl)

            val intent = Intent(context, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun updateUser(data:String, data1: String, data2:String, data3:String, data4:String, data5:String, data6:String, data7:String) {
        AndroidNetworking.post("http://192.168.0.7/api-rapidtest/pasien/update.pasien.php")
            .addBodyParameter("username", data)
            .addBodyParameter("nama_user", data1)
            .addBodyParameter("alamat", data2)
            .addBodyParameter("nohp_user", data3)
            .addBodyParameter("umur", data4)
            .addBodyParameter("jeniskelamin", data5)
            .addBodyParameter("goldarah", data6)
            .addBodyParameter("ttl_pasien", data7)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {

                }

                override fun onError(anError: ANError) {

                }

            })
    }

    fun getUsers(data:String) {
        AndroidNetworking.post("http://192.168.0.7/api-rapidtest/pasien/update.pasien.php")
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
