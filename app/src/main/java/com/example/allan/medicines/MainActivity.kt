package com.example.allan.medicines

import android.content.Intent
import android.icu.util.ULocale
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.Calendar.*

class MainActivity : AppCompatActivity() {

    lateinit var medicina : EditText
    lateinit var clase : EditText
    lateinit var aplicacion : EditText
    lateinit var vencimiento: EditText
    lateinit var precio : EditText
    lateinit var dia: EditText
    lateinit var mes: EditText
    lateinit var año: EditText

    var calendar = GregorianCalendar(Locale.getDefault())
    var diaSys = calendar.get(Calendar.DAY_OF_MONTH)
    var mesSys = calendar.get(Calendar.MONTH) + 1
    var añoSys = calendar.get(Calendar.YEAR)


    lateinit var btnguardar : Button
    lateinit var inventario : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        medicina    = findViewById(R.id.nombre)
        clase   = findViewById(R.id.clasificacion)
        aplicacion   = findViewById(R.id.aplicacion)
        vencimiento  = findViewById(R.id.dia)
        precio  = findViewById(R.id.precio)
        dia = findViewById(R.id.dia)
        mes = findViewById(R.id.mes)
        año = findViewById(R.id.año)

        Toast.makeText(this,"$diaSys/$mesSys/$añoSys",Toast.LENGTH_LONG).show()
        btnguardar   = findViewById(R.id.btn1)
        inventario   = findViewById(R.id.btn2)


        btnguardar.setOnClickListener {
                guardar()
        }
        inventario.setOnClickListener {
            val intent = Intent(this, Mostrar::class.java)
            startActivity(intent)
        }
    }
    private fun guardar(){
        val nombre    = medicina.text.toString().trim()
        val clas    = clase.text.toString().trim()
        val ap      = aplicacion.text.toString().trim()
        val fecha   = vencimiento.text.toString().trim()
        val pre   = precio.text.toString().trim()

        if (nombre.isEmpty()){
            medicina.error = "Ingrese el dato de forma correcta"
            return
        }
        if (clas.isEmpty()){
            clase.error = "Ingrese el dato de forma correcta"
            return
        }
        if (ap.isEmpty()){
            aplicacion.error = "Ingrese el dato de forma correcta"
            return
        }
        if (fecha.isEmpty()){
            vencimiento.error = "Ingrese el dato de forma correcta"
            return
        }
        if (pre.isEmpty()){
            precio.error = "Ingrese el dato de forma correcta"
            return
        }
        val BDD = FirebaseDatabase.getInstance().getReference("Medicinas")
        val Id = BDD.push().key
        val medicinas = Medicine(Id!!,nombre,clas,ap,fecha,pre)

        if (Id != null) {
            BDD.child(Id).setValue(medicinas).addOnCompleteListener{
                Toast.makeText(this,"Guardado Exitosamente",Toast.LENGTH_LONG).show()
            }
        }






    }
}

