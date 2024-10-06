package ar.com.parcial

import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HistorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val prefs = getSharedPreferences("HistorialPrefs", Context.MODE_PRIVATE)
        val historial = prefs.getStringSet("historial", emptySet())?.toList() ?: emptyList()

        if (historial.isNotEmpty()) {

            val ultimasComparaciones = historial.takeLast(5)

            val historialContainer = findViewById<LinearLayout>(R.id.llHistorialContainer)

            historialContainer.removeAllViews()

            for (comparacion in ultimasComparaciones) {
                val textView = TextView(this)
                textView.text = comparacion
                textView.textSize = 16f
                textView.setPadding(0, 8, 0, 8)
                historialContainer.addView(textView)
            }
        } else {

            val textViewNoHistorial = TextView(this)
            textViewNoHistorial.text = "No hay comparaciones guardadas"
            textViewNoHistorial.textSize = 16f
            textViewNoHistorial.setPadding(0, 8, 0, 8)
            findViewById<LinearLayout>(R.id.llHistorialContainer).addView(textViewNoHistorial)
        }
    }
}


