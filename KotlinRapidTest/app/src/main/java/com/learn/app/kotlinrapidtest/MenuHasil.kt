package com.learn.app.kotlinrapidtest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_menu_hasil.*
import kotlinx.android.synthetic.main.activity_menu_hasil.view.*
import kotlinx.android.synthetic.main.activity_menu_rumah_sakit.*
import kotlinx.android.synthetic.main.list_riwayat.*
import org.json.JSONObject

class MenuHasil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_hasil)

        val context = this

        kembali_hasil.setOnClickListener {
            val kembali = Intent(context, DashboardActivity::class.java)
            startActivity(kembali)
        }

        val sharedPref = getSharedPreferences("PROFIL", Context.MODE_PRIVATE)
        val dtusername = sharedPref?.getString("username", "").toString()

        getUsers(dtusername)

        val user = getSharedPreferences("PASIEN", Context.MODE_PRIVATE)
        val dtnama_pasien = user?.getString("nama", "").toString()

        getStatusAntrian(dtnama_pasien)

        getRiwayat(dtnama_pasien)

    }

    fun getRiwayat(data: String) {

        val riwayat = ArrayList<Riwayat>()

        val recyclerView = findViewById(R.id.listRiwayat) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        AndroidNetworking.post("http://192.168.0.7/api-rapidtest/report/read.report.php")
            .addBodyParameter("nama_pasien", data)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("nama_pasien"))

                        var isi1 = jsonObject.optString("nama_rs").toString()
                        var isi2 = jsonObject.optString("status").toString()
                        var isi3 = jsonObject.optString("keterangan").toString()

                        riwayat.add(Riwayat("$isi1", "$isi2", "$isi3"))
                    }

                    val sharedPref = getSharedPreferences("PROFIL", Context.MODE_PRIVATE)
                    val dtusername = sharedPref?.getString("username", "").toString()

                    getUsers(dtusername)

                    val user = getSharedPreferences("PASIEN", Context.MODE_PRIVATE)
                    val dtnama_pasien = user?.getString("nama", "").toString()

                    getStatusAntrian(dtnama_pasien)

                    val adapter = AdapterRiwayat(riwayat)
                    recyclerView.adapter = adapter
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
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

    fun getStatusAntrian(data: String) {
        AndroidNetworking.post("http://192.168.0.7/api-rapidtest/antrian/read.antrian.php")
            .addBodyParameter("nama_pasien", data)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
//                        Log.e("_kotlinTitle", jsonObject.optString("nama_pasien"))
                        val nama_pasiens: String = jsonObject.optString("nama_pasien")
                        val nama_rs: String = jsonObject.optString("nama_rs")
                        val jadwalpilihan: String = jsonObject.optString("jadwalpilihan")
                        val status: String = jsonObject.optString("Status")

                        hasil_nama.text = nama_pasiens
                        hasil_rs.text = nama_rs
                        hasil_jadwal.text = jadwalpilihan
                        hasil_proses.text = status

                        if(jsonArray.length() == 0){
                            hasil_nama.text = "BELUM ADA ANTRIAN"
                        }

                    }
                }

                override fun onError(anError: ANError?) {

                }
            })

    }
}
