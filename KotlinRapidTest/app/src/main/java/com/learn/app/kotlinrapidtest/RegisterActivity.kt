package com.learn.app.kotlinrapidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONArray

class RegisterActivity : AppCompatActivity() {

    private var regShowPass = false
    private var regShowKonfirmPass = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val context = this

        back_register.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

        btn_daftar.setOnClickListener {
                var reg_username:String = reg_username.text.toString()
                var reg_password:String = reg_password.text.toString()
                var reg_konfirm_pass:String = reg_konfirm_pass.text.toString()
                var reg_nama:String = reg_nama.text.toString()
                var reg_alamat:String = reg_alamat.text.toString()
                var reg_nohp:String = reg_no_hp.text.toString()
                var reg_umur:String = reg_umur.text.toString()
                var reg_jenis_kelamin:String = reg_jenis_kelamin.text.toString()
                var reg_gol_darah:String = reg_gol_darah.text.toString()
                var reg_ttl:String = reg_ttl.text.toString()

                if (reg_password == reg_konfirm_pass) {
                    konfirm_password.setText("")
                    inputdata(reg_username,reg_password, reg_konfirm_pass, reg_nama,  reg_alamat, reg_nohp, reg_umur, reg_jenis_kelamin, reg_gol_darah, reg_ttl)
                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    konfirm_password.setText("Password not match!")
                }
            }

        reg_show_password.setOnClickListener{
            regShowPass = !regShowPass
            showPassword(regShowPass)
        }

        showPassword(regShowPass)

        reg_show_konfirm_passwod.setOnClickListener{
            regShowKonfirmPass = !regShowKonfirmPass
            showKonfirmPassword(regShowKonfirmPass)
        }

        showKonfirmPassword(regShowKonfirmPass)

    }

    private fun showPassword(isShow: Boolean) {
        if (isShow) {
            reg_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            reg_show_password.setImageResource(R.drawable.show_password)
        } else {
            reg_password.transformationMethod = PasswordTransformationMethod.getInstance()
            reg_show_password.setImageResource(R.drawable.hide_password)
        }
        reg_password.setSelection(reg_password.text.toString().length)
    }

    private fun showKonfirmPassword(isShow: Boolean) {
        if (isShow) {
            reg_konfirm_pass.transformationMethod = HideReturnsTransformationMethod.getInstance()
            reg_show_konfirm_passwod.setImageResource(R.drawable.show_password)
        } else {
            reg_konfirm_pass.transformationMethod = PasswordTransformationMethod.getInstance()
            reg_show_konfirm_passwod.setImageResource(R.drawable.hide_password)
        }
        reg_konfirm_pass.setSelection(reg_konfirm_pass.text.toString().length)
    }

    fun inputdata(data1: String, data2: String, data3: String, data4: String, data5: String, data6: String, data7: String, data8: String, data9: String, data10: String) {
        AndroidNetworking.post("http://192.168.0.7/api-rapidtest/pasien/create.pasien.php")
            .addBodyParameter("username", data1)
            .addBodyParameter("password", data2)
            .addBodyParameter("konfirm_pass", data3)
            .addBodyParameter("nama_user", data4)
            .addBodyParameter("alamat", data5)
            .addBodyParameter("nohp_user", data6)
            .addBodyParameter("umur", data7)
            .addBodyParameter("jeniskelamin", data8)
            .addBodyParameter("goldarah", data9)
            .addBodyParameter("ttl_pasien", data10)
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
