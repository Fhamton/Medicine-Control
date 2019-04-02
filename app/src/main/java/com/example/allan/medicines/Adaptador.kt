package com.example.allan.medicines

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class Adaptador(val contexto : Context, val layoutId:Int, val listaMedicinas:List<Medicine>)
    : ArrayAdapter<Medicine>(contexto,layoutId,listaMedicinas){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(contexto)
        val view: View = layoutInflater.inflate(layoutId,null)

        val nombre = view.findViewById<TextView>(R.id.tvnombre)
        val clas = view.findViewById<TextView>(R.id.tvClass)
        val ap = view.findViewById<TextView>(R.id.tvApli)
        val fecha = view.findViewById<TextView>(R.id.tvVen)
        val pre = view.findViewById<TextView>(R.id.tvPrecio)

        val actualizar = view.findViewById<TextView>(R.id.btnAct)
        val borrar = view.findViewById<TextView>(R.id.btnElim)

        val medicina = listaMedicinas[position]

        nombre.text = medicina.nonbre
        clas.text = medicina.clas
        ap.text = medicina.ap
        fecha.text = medicina.fecha
        pre.text = medicina.pre

        actualizar.setOnClickListener {
            actualizarInfo(medicina)
        }

        borrar.setOnClickListener {
            borrarInfo(medicina)
        }

        return view
    }

    private  fun actualizarInfo(medicina:Medicine){

        val builder = AlertDialog.Builder(contexto)
        builder.setTitle("Update Info")
        val inflater = LayoutInflater.from(contexto)
        val view = inflater.inflate(R.layout.activity_actualizar,null)

        val nombre = view.findViewById<TextView>(R.id.firstnameUpdate)
        val clas = view.findViewById<TextView>(R.id.firstnameUpdate2)
        val ap = view.findViewById<TextView>(R.id.firstnameUpdate3)
        val fecha = view.findViewById<TextView>(R.id.firstnameUpdate4)
        val pre = view.findViewById<TextView>(R.id.firstnameUpdate5)

        nombre.setText(medicina.nonbre)
        clas.setText(medicina.clas)
        ap.setText(medicina.ap)
        fecha.setText(medicina.fecha)
        pre.setText(medicina.pre)

        builder.setView(view)

        builder.setPositiveButton("Actualizar",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

                val  BDD = FirebaseDatabase.getInstance().getReference("Medicinas")

                val nonb    = nombre.text.toString().trim()
                val cla     = clas.text.toString().trim()
                val apl     = ap.text.toString().trim()
                val fech   = fecha.text.toString().trim()
                val prec   = pre.text.toString().trim()

                if (nonb.isEmpty()){
                    nombre.error = "Please enter your firstname"
                    return
                }
                if (cla.isEmpty()){
                    clas.error = "Please enter your lastname"
                    return
                }
                if (apl.isEmpty()){
                    ap.error = "Please enter your address"
                    return
                }
                if (fech.isEmpty()){
                    fecha.error = "Please enter your department"
                    return
                }
                if (prec.isEmpty()){
                    pre.error = "Please enter your department"
                    return
                }

                val medicina = Medicine(medicina.id,nonb,cla,apl,fech,prec)
                BDD.child(medicina.id).setValue(medicina)
                Toast.makeText(contexto,"Actualizar", Toast.LENGTH_LONG).show()
            }})

        builder.setNegativeButton("cancelar",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

            }
        })

        val alert = builder.create()
        alert.show()
    }
    private fun borrarInfo(medicina:Medicine){
        val BDD = FirebaseDatabase.getInstance().getReference("Medicinas")
        BDD.child(medicina.id).removeValue()
        Toast.makeText(contexto,"Eliminado", Toast.LENGTH_LONG).show()
    }
}