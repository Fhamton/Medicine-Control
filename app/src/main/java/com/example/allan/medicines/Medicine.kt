package com.example.allan.medicines

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Medicine : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine)


        val TAG = "com.example.allan.Main.medicines"
        var btnMedicine = findViewById<Button>(R.id.btnAct)

        btnMedicine.setOnClickListener {
            val intent = Intent(this, Actualizar::class.java)
            startActivity(intent)
        }
    }
}