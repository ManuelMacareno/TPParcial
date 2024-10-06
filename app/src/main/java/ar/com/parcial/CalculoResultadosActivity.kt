package ar.com.parcial

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculoResultadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculo_resultados)

        val monto1 = intent.getDoubleExtra("monto1", 0.0)
        val tasa1 = intent.getDoubleExtra("tasa1", 0.0)
        val plazo1 = intent.getIntExtra("plazo1", 0)

        val monto2 = intent.getDoubleExtra("monto2", 0.0)
        val tasa2 = intent.getDoubleExtra("tasa2", 0.0)
        val plazo2 = intent.getIntExtra("plazo2", 0)

        val rendimiento1 = calcularROI(monto1, tasa1, plazo1)
        val rendimiento2 = calcularROI(monto2, tasa2, plazo2)

        val rendimiento1Formateado = String.format("%.5f", rendimiento1)
        val rendimiento2Formateado = String.format("%.5f", rendimiento2)

        findViewById<TextView>(R.id.tvResultados).text =
            "Inversión 1: $rendimiento1Formateado\nInversión 2: $rendimiento2Formateado"

        val recomendacion = if (rendimiento1 > rendimiento2) {
            "La mejor opción es la Inversión 1"
        } else {
            "La mejor opción es la Inversión 2"
        }
        findViewById<TextView>(R.id.tvRecomendacion).text = recomendacion

        val comparacion = "Inversión 1: $rendimiento1Formateado, Inversión 2: $rendimiento2Formateado \n - $recomendacion \n"
        guardarComparacionEnHistorial(comparacion)
    }

    private fun calcularROI(monto: Double, tasa: Double, plazo: Int): Double {
        return monto * (tasa / 100) * plazo / 12
    }

    private fun guardarComparacionEnHistorial(comparacion: String) {
        val prefs = getSharedPreferences("HistorialPrefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()

        val historial = prefs.getStringSet("historial", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        historial.add(comparacion)

        editor.putStringSet("historial", historial)
        editor.apply()
    }
}
