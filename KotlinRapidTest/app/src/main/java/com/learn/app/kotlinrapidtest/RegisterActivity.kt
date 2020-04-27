package com.learn.app.kotlinrapidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONArray

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val context = this
        btn_daftar.setOnClickListener {
            var reg_username:String = reg_username.text.toString()
            var reg_password:String = reg_password.text.toString()
            var reg_nama:String = reg_nama.text.toString()
            var reg_alamat:String = reg_alamat.text.toString()
            var reg_nohp:String = reg_no_hp.text.toString()
            var reg_umur:String = reg_umur.text.toString()
            var reg_jenis_kelamin:String = reg_jenis_kelamin.text.toString()
            var reg_gol_darah:String = reg_gol_darah.text.toString()
            var reg_ttl:String = reg_ttl.text.toString()

            inputdata(reg_username,reg_password, reg_nama,  reg_alamat, reg_nohp, reg_umur, reg_jenis_kelamin, reg_gol_darah, reg_ttl)

            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun inputdata(data1: String, data2: String, data3: String, data4: String, data5: String, data6: String, data7: String, data8: String, data9: String) {
        AndroidNetworking.post("http://192.168.0.5/api-rapidtest/pasien/create.pasien.php")
            .addBodyParameter("username", data1)
            .addBodyParameter("password", data2)
            .addBodyParameter("nama_user", data3)
            .addBodyParameter("alamat", data4)
            .addBodyParameter("nohp_user", data5)
            .addBodyParameter("umur", data6)
            .addBodyParameter("jeniskelamin", data7)
            .addBodyParameter("goldarah", data8)
            .addBodyParameter("ttl_pasien", data9)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {

                }

                override fun onError(anError: ANError?) {

                }
            })

    }
}
