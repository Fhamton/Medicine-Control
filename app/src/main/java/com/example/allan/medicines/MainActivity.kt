package com.example.allan.medicines

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TAG = "com.example.allan.Main.medicines"
        var btnMedicine = findViewById<Button>(R.id.btn2)

        btnMedicine.setOnClickListener {
            val intent = Intent(this, Medicine::class.java)
            startActivity(intent)
        }
    }
}