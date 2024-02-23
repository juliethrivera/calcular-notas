package com.julieth.rivera.calcularnotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    private lateinit var ingresarNombre : EditText
    private lateinit var ingresarPorcetaje : EditText
    private lateinit var ingresarNota : EditText
    private lateinit var finalizar : Button
    private lateinit var guardar : Button
    private lateinit var progreso: ProgressBar


    private var porcentajeAcumulado = 0

    val listaNotas : MutableList<Double> = mutableListOf()
    val listaPorcentaje : MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ingresarNombre = findViewById(R.id.ingresarNombre)
        ingresarPorcetaje = findViewById(R.id.ingresarPorcentaje)
        ingresarNota = findViewById(R.id.ingresarNota)
        finalizar = findViewById(R.id.finalizar)
        guardar = findViewById(R.id.guardar)
        progreso = findViewById(R.id.progreso)

        guardar.setOnClickListener {

            val nota = (ingresarNota.text.toString())

            val porcentaje = (ingresarPorcetaje.text.toString())

            val nombre = ingresarNombre.text.toString()

            if (nota.isNullOrEmpty() || porcentaje.isNullOrEmpty() || nombre.isNullOrEmpty()){

                Toast.makeText(this, "los datos ingresados no son validos",
                     Toast.LENGTH_LONG ).show()
                return@setOnClickListener
            }


            if (validarNota(nota.toDouble()) && validarPorcentaje(porcentaje.toInt()) && validarNombre(nombre)){
                listaNotas.add(nota.toDouble())

                listaPorcentaje.add(porcentaje.toInt())
                porcentajeAcumulado += porcentaje.toInt()
                actualizarProgreso(porcentajeAcumulado)
                ingresarNombre.isEnabled = false
                ingresarNota.text.clear()
                ingresarPorcetaje.text.clear()

                Toast.makeText(this, "la nota ingresada es correcta",
                    Toast.LENGTH_LONG).show()


            }else{

                Toast.makeText(this, "los datos ingresados no son validos",
                    Toast.LENGTH_LONG).show()
            }


        }

    }


     fun actualizarProgreso(porcentaje: Int){

         progreso.progress = porcentaje

         if (porcentaje >=100) {
             finalizar.isEnabled = true
         }
     }
    fun validarNota(nota : Double) : Boolean{

        return nota >= 0 && nota <= 5

    }


    //validamos los porcentajes
    fun validarPorcentaje (porcentaje : Int ) : Boolean{

        return  porcentajeAcumulado + porcentaje <=100



    }

    fun validarNombre(nombre: String) : Boolean{
        return !nombre.matches(Regex(".*\\d." ))
    }




}


