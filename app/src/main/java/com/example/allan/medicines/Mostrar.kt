package com.example.allan.medicines

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.google.firebase.database.*

class Mostrar : AppCompatActivity() {

    lateinit var BDD : DatabaseReference
    lateinit var listaMedicinas:MutableList<Medicine>
    lateinit var listview: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.template)
        listaMedicinas = mutableListOf()
        listview = findViewById(R.id.lv1)
        BDD = FirebaseDatabase.getInstance().getReference("Medicinas")

        BDD.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError)
            {

            }
            override fun onDataChange(p0: DataSnapshot)
            {
                if (p0!!.exists())
                {
                    listaMedicinas.clear()
                    for (e in p0.children){
                        val medicina = e.getValue(Medicine::class.java)
                        listaMedicinas.add(medicina!!)
                    }
                }
                val adaptador = Adaptador(this@Mostrar,R.layout.activity_medicine, listaMedicinas)
                listview.adapter = adaptador
            }
        })
    }
}