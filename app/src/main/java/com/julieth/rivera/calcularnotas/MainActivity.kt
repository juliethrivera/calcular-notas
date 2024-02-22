package com.julieth.rivera.calcularnotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // declaramos la variable
    private lateinit var ingresarNombre : EditText
    private lateinit var ingresarPorcetaje : EditText
    private lateinit var ingresarNota : EditText
    private lateinit var finalizar : Button
    private lateinit var guardar : Button
    private lateinit var progreso: ProgressBar

    //variable global para validar el porcentaje
    private var porcentajeAcumulado = 0

    val listaNotas : MutableList<Double> = mutableListOf()
    val listaPorcentaje : MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //inicializamos la variable
        ingresarNombre = findViewById(R.id.ingresarNombre)
        ingresarPorcetaje = findViewById(R.id.ingresarPorcentaje)
        ingresarNota = findViewById(R.id.ingresarNota)
        finalizar = findViewById(R.id.finalizar)
        guardar = findViewById(R.id.guardar)
        progreso = findViewById(R.id.progreso)

        guardar.setOnClickListener {

            val nota = (ingresarNota.text.toString()).toDouble()

            val porcentaje = (ingresarPorcetaje.text.toString()).toInt()

            val nombre = ingresarNombre.text.toString()




            if (validarNota(nota) && validarPorcentaje(porcentaje)){
                listaNotas.add(nota)

                listaPorcentaje.add(porcentaje)
                porcentajeAcumulado += porcentaje

                ingresarNota.text.clear()
                ingresarPorcetaje.text.clear()


            }else{
                //TODO informar al usuario que la nota ingresada no es valida
            }


        }

    }

    fun actualizarProgreso(porcentaje: Int){
        progreso.progress = porcentaje


    }
    fun validarNota(nota : Double) : Boolean{

        return nota >= 0 && nota <= 5

    }


    //validamos los porcentajes
    fun validarPorcentaje (porcentaje : Int ) : Boolean{

        return  porcentajeAcumulado + porcentaje <=100


    }
}



