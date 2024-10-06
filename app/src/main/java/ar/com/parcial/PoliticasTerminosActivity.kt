package ar.com.parcial

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PoliticasTerminosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_politicas_terminos)

        val politicas = getString(R.string.politicas_terminos)
        findViewById<TextView>(R.id.tvPoliticas).text = politicas
    }
}
