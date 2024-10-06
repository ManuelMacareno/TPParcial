package ar.com.parcial

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RendimientoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rendimiento)

        val monto1 = intent.getDoubleExtra("monto1", 0.0)
        val tasa1 = intent.getDoubleExtra("tasa1", 0.0)
        val plazo1 = intent.getIntExtra("plazo1", 0)

        val monto2 = intent.getDoubleExtra("monto2", 0.0)
        val tasa2 = intent.getDoubleExtra("tasa2", 0.0)
        val plazo2 = intent.getIntExtra("plazo2", 0)

        val roi1 = calcularROI(monto1, tasa1, plazo1)
        val roi2 = calcularROI(monto2, tasa2, plazo2)

        val resultados = "Inversión 1 ROI: $roi1%\nInversión 2 ROI: $roi2%"
        findViewById<TextView>(R.id.tvResultados).text = resultados

        findViewById<Button>(R.id.btnVolver).setOnClickListener {
            finish()
        }

    }
    fun calcularROI(montoInicial: Double, tasa: Double, plazo: Int): Double {
        val tasaMensual = tasa / 12 / 100
        val capitalFinal = montoInicial * Math.pow((1 + tasaMensual), plazo.toDouble())
        val roi = (capitalFinal - montoInicial) / montoInicial * 100
        return roi
    }
}
