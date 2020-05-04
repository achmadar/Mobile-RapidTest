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
import kotlinx.android.synthetic.main.activity_menu_rumah_sakit.*
import org.json.JSONObject
import java.text.FieldPosition

class MenuRumahSakit : AppCompatActivity(), AdapterRumahSakit.OnItemClickListener {

    override fun onItemClick(rs: RumahSakit) {
//        send data to detailed activity
        var intent = Intent(this@MenuRumahSakit, MenuBooking::class.java)
        intent.putExtra("nama_rs", rs.nama)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_rumah_sakit)

        val context = this

        kembali_rs.setOnClickListener {
            val kembali = Intent(context, DashboardActivity::class.java)
            startActivity(kembali)
        }

        val recyclerView = findViewById(R.id.list_rs) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val rs = ArrayList<RumahSakit>()

        AndroidNetworking.get("http://192.168.0.7/api-rapidtest/rumahsakit/read.rumahsakit.php")
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
                        var isi2 = jsonObject.optString("alamat_rs").toString()
                        var isi3 = jsonObject.optString("notelp_rs").toString()
                        var isi4 = jsonObject.optString("jadwal_rs").toString()

                        rs.add(RumahSakit("$isi1", "$isi2", "$isi3", "$isi4"))
                    }

                    val adapter = AdapterRumahSakit(rs, this@MenuRumahSakit)
                    recyclerView.adapter = adapter
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }

            })

    }

}
