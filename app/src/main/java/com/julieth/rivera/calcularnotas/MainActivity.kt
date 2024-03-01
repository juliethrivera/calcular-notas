package com.julieth.rivera.calcularnotas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.julieth.rivera.calcular_notas.R

class MainActivity : AppCompatActivity() {

    private lateinit var progreso : ProgressBar
    private lateinit var ingresarNombre : EditText
    private lateinit var ingresarPorcentaje : EditText
    private lateinit var ingresarNota: EditText
    private lateinit var finalizar : Button
    private lateinit var guardar : Button
    private lateinit var vistaPromedio : TextView
    private lateinit var vistaNotaFinal: TextView
    private lateinit var siguienteEstudiante: Button
    private var estudianteActual : Estudiante = Estudiante()

    private var porcentajeAcumulado = 0
    val listaNotas : MutableList<Double> = mutableListOf()
    val listaPorcentaje : MutableList<Int> = mutableListOf()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main )


        progreso = findViewById(R.id.progreso)
        ingresarNombre = findViewById(R.id.ingresarNombre)
        ingresarPorcentaje = findViewById(R.id.ingresarPorcentaje)
        ingresarNota = findViewById(R.id.ingresarNota)
        finalizar = findViewById(R.id.finalizar)
        guardar = findViewById(R.id.guardar)
        siguienteEstudiante = findViewById(R.id.ingresarEstudiante)

        finalizar.setOnClickListener{
            vistaNotaFinal.text = "nota final :" + estudianteActual.notaFinal()
            vistaPromedio.text = "promedio :" + estudianteActual.calcularPromedio()
            siguienteEstudiante.isEnabled = true
        }

        guardar.setOnClickListener {
            val nota = (ingresarNota.text.toString())
            val porcentaje = (ingresarPorcentaje.text.toString())
            val nombre = (ingresarNombre.text.toString())



            if (validarVacio(nombre, nota, porcentaje)){
                if (validarNombre(nombre) &&
                    validarNota(nota.toDouble()) &&
                    validarPorcentaje((porcentaje.toInt()))
                ){
                    listaNotas.add(nota.toDouble())
                    listaPorcentaje.add(porcentaje.toInt())

                    porcentajeAcumulado += porcentaje.toInt()

                    actualizarProgress(porcentajeAcumulado)

                    ingresarNombre.isEnabled = false
                    ingresarNota.text.clear()
                    ingresarPorcentaje.text.clear()

                    mostrarMensaje("la nota fue ingresada correctamente")
                }else{
                    mostrarMensaje("verifique los datos ingresados")
                }
            }else{
                mostrarMensaje("Datos incompletos")
            }
        }
    }
    fun actualizarProgress(porcentaje : Int) {
        progreso.progress = porcentaje
        if (porcentaje >= 100){
            finalizar.isEnabled = true
            estudianteActual.nombre = (ingresarNota.text.toString())
            estudianteActual.porcentaje = listaPorcentaje
            estudianteActual.notas = listaNotas
        }
    }
    fun mostrarMensaje(mensaje : String){
        Toast.makeText(this,
            mensaje,
            Toast.LENGTH_LONG).show()
    }
    fun validarVacio(nombre: String, nota : String, porcentaje: String): Boolean{
        return !nombre.isNullOrEmpty() && !nota.isNullOrEmpty() && !porcentaje.isNullOrEmpty()
    }
    fun validarNombre(nombre : String): Boolean {
        return !nombre.matches(Regex(".*\\d.*"))

    }
    fun validarNota(nota : Double) : Boolean{
        return nota >= 0 && nota <= 5
    }
    fun validarPorcentaje(porcentaje : Int ) : Boolean{
        return porcentajeAcumulado + porcentaje <=100
    }


}

class Estudiante (){

    var nombre : String = ""
    var notas : List<Double> = listOf()
    var porcentaje : List<Int> = listOf()

    fun calcularPromedio() : Double{
        var sumaNotas = 0.0
        for( n in notas){
            sumaNotas += n
        }

        return sumaNotas / notas.size


    }


    fun notaFinal() : Double{
        var notaFinal : Double = 0.0
        var contador = 0
        for (n in notas){
            notaFinal += (n * porcentaje[contador]) / 100
            contador++
        }

         return notaFinal
    }


}