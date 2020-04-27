package com.learn.app.kotlinrapidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_dashboard.*

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

        var menu : Menu = navigation.menu
        selectedMenu(menu.getItem(0))
        navigation.setOnNavigationItemSelectedListener {
                item: MenuItem ->  selectedMenu(item)

            false
        }

    }

    private fun selectedMenu(item : MenuItem) {
        item.isChecked = true
        when(item.itemId) {
            R.id.navigation_home -> selectedFragment(ProfileFragment.getInstance())
        }
    }

    fun selectedFragment(fragment: Fragment) {
        var transaction : android.app.FragmentTransaction? = fragmentManager.beginTransaction()
        transaction?.replace(R.id.fragment, fragment)
        transaction?.commit()

    }
}
