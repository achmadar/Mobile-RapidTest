package com.learn.app.kotlinrapidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_menu_antrian.*
import kotlinx.android.synthetic.main.activity_menu_rumah_sakit.*
import org.json.JSONObject

class MenuAntrian : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_antrian)

        val context = this

        kembali_antrian.setOnClickListener {
            val kembali = Intent(context, DashboardActivity::class.java)
            startActivity(kembali)
        }

        val recyclerView = findViewById(R.id.list_antrian) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val antrian = ArrayList<Antrian>()

        AndroidNetworking.get("http://192.168.0.7/api-rapidtest/antrian/read.antrian.rs.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("nama_rs"))

                        var isi1 = jsonObject.optString("nama_rs").toString()
                        var isi2 = jsonObject.optString("antrian").toString()+"/50"

                        antrian.add(Antrian("$isi1", "$isi2"))
                    }

                    val adapter = AdapterAntrian(antrian)
                    recyclerView.adapter = adapter
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }

            })


    }
}
