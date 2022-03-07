package com.honeykoders.bankodemia.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.honeykoders.bankodemia.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llamarFragment()
    }

    fun llamarFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = Transferencia()
        fragmentTransaction.add(R.id.list, fragment)
        fragmentTransaction.commit()
    }

}