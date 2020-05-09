package com.learn.app.kotlinrapidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_menu_booking.*
import kotlinx.android.synthetic.main.activity_menu_rumah_sakit.*
import kotlinx.android.synthetic.main.list_antrian.*
import org.json.JSONArray
import org.json.JSONObject

class MenuBooking : AppCompatActivity(), AdapterBooking.OnItemClickListener {

    override fun onItemClick(book: Booking) {
//        send data to detailed activity
        var intent = Intent(this@MenuBooking, BookingActivity::class.java)
        intent.putExtra("nama_rs", book.nama)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_booking)

        val context = this

        kembali_booking.setOnClickListener {
            val kembali = Intent(context, DashboardActivity::class.java)
            startActivity(kembali)
        }

        val recyclerView = findViewById(R.id.list_booking) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val book = ArrayList<Booking>()

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
                        var isi2 = jsonObject.optString("jadwal_rs").toString()

                        book.add(Booking("$isi1", "$isi2"))
                    }

                    val adapter = AdapterBooking(book, this@MenuBooking)
                    recyclerView.adapter = adapter
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }

            })

    }

}
