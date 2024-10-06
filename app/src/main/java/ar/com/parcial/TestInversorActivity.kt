package ar.com.parcial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TestInversorActivity : AppCompatActivity() {

    private val preguntasYRespuestas = listOf(
        Pair("¿Qué experiencia tenés como inversionista?" ,
            listOf("Ninguna", "Baja", "Media", "Alta")),
        Pair("¿Aproximadamente, qué porcentaje de tus ingresos mensuales ahorrás por mes?",
            listOf("Menos del 5%", "Entre el 5% y el 20%", "Entre el 20% y el 50%", "Más del 50%")),
        Pair("¿Qué porcentaje de tus ahorros estás dispuesto a destinar a las inversiones en el Mercado de Capitales?",
            listOf("Menos del 25%", "Entre el 25% y el 40%", "Entre el 41% y el 65%", "Más del 65%"))
    )

    private var respuestasSeleccionadas = mutableListOf<String>()
    private var preguntaActual = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_inversor)

        val tvPregunta = findViewById<TextView>(R.id.tvPregunta)
        val rgOpciones = findViewById<RadioGroup>(R.id.rgOpciones)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)

        mostrarPregunta(tvPregunta, rgOpciones)

        btnEnviar.setOnClickListener {
            val seleccionada = findViewById<RadioButton>(rgOpciones.checkedRadioButtonId)
            if (seleccionada != null) {
                respuestasSeleccionadas.add(seleccionada.text.toString())

                if (preguntaActual < preguntasYRespuestas.size - 1) {
                    preguntaActual++
                    mostrarPregunta(tvPregunta, rgOpciones)
                } else {
                    val perfilInversor = clasificarPerfil()
                    guardarDatos(perfilInversor)
                    Toast.makeText(this, "Test completado. Perfil: $perfilInversor", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, WelcomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(this, "Seleccione una opción", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun mostrarPregunta(tvPregunta: TextView, rgOpciones: RadioGroup) {
        val (pregunta, opciones) = preguntasYRespuestas[preguntaActual]
        tvPregunta.text = pregunta
        rgOpciones.removeAllViews()

        opciones.forEach { opcion ->
            val radioButton = RadioButton(this)
            radioButton.text = opcion
            rgOpciones.addView(radioButton)
        }
    }

    private fun clasificarPerfil(): String {
        var puntaje = 0

        respuestasSeleccionadas.forEach { respuesta ->
            puntaje += when (respuesta) {
                "Ninguna", "Menos del 5%", "Menos del 25%" -> 1
                "Baja", "Entre el 5% y el 20%", "Entre el 25% y el 40%" -> 2
                "Media", "Entre el 20% y el 50%", "Entre el 41% y el 65%" -> 3
                "Alta", "Más del 50%", "Más del 65%" -> 4
                else -> 0
            }
        }

        return when {
            puntaje <= 4 -> "Conservador"
            puntaje in 5..8 -> "Moderado"
            else -> "Agresivo"
        }
    }

    private fun guardarDatos(perfilInversor: String) {
        val prefs = getSharedPreferences("TestInversorPrefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("perfil", perfilInversor)
        editor.putStringSet("respuestas", respuestasSeleccionadas.toSet())
        editor.apply()
    }
}








