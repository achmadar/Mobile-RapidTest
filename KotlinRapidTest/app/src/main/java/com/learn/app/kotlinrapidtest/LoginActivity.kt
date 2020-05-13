package com.learn.app.kotlinrapidtest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private var showPass = false
    var username: String = ""
    var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val context = this

        back_start.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }

        btn_register.setOnClickListener {

            val intent = Intent(context, RegisterActivity::class.java)
            startActivity(intent)
        }

        val sharedPreferences = getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
        val stat = sharedPreferences.getString("STATUS", "")

        if (stat=="1") {

            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
            finish()

        } else {

            btn_login.setOnClickListener {

                username = log_username.text.toString()
                password = log_password.text.toString()

                postkeserver(username, password)

            }
        }

        ibShowPassword.setOnClickListener{
            showPass = !showPass
            showPassword(showPass)
        }

        showPassword(showPass)

    }

    private fun showPassword(isShow: Boolean) {
        if (isShow) {
            log_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            ibShowPassword.setImageResource(R.drawable.show_password)
        } else {
            log_password.transformationMethod = PasswordTransformationMethod.getInstance()
            ibShowPassword.setImageResource(R.drawable.hide_password)
        }
        log_password.setSelection(log_password.text.toString().length)
    }

    fun postkeserver(data1: String, data2: String) {

        AndroidNetworking.post("http://192.168.0.7/api-rapidtest/pasien/ceklogin.php")
            .addBodyParameter("username", data1)
            .addBodyParameter("password", data2)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("status"))
                        var statusLogin = jsonObject.optString("status")

                        if(statusLogin=="1") {
                            val sharedPreferences = getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
                            val editor = sharedPreferences.edit()

                            editor.putString("STATUS", statusLogin)
                            editor.apply()

                            val profil = getSharedPreferences("PROFIL", Context.MODE_PRIVATE)
                            val edit = profil.edit()
                            edit.putString("username", username)
                            edit.putString("password", password)
                            edit.apply()

                            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                            finish()

                        } else {
                            log_username.setText("")
                            log_password.setText("")
                            info.setText("Username atau Password Salah!")
                        }

                    }

                }

                override fun onError(anError: ANError?) {

                }

            })

    }
}

