package ar.com.parcial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnIngresoInversion).setOnClickListener {
            startActivity(Intent(this, IngresoInversionActivity::class.java))
        }

        findViewById<Button>(R.id.btnHistorial).setOnClickListener {
            startActivity(Intent(this, HistorialActivity::class.java))
        }

        findViewById<Button>(R.id.btnPoliticasTerminos).setOnClickListener {
            startActivity(Intent(this, PoliticasTerminosActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        checkFirstRun()
        displayUserInfo()
    }

    private fun checkFirstRun() {
        val prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isFirstRun = prefs.getBoolean("isFirstRun", true)

        if (isFirstRun) {
            startActivity(Intent(this, FormularioActivity::class.java))
            prefs.edit().putBoolean("isFirstRun", false).apply()
            finish()
        }
    }

    private fun displayUserInfo() {
        val prefs = getSharedPreferences("TestInversorPrefs", Context.MODE_PRIVATE)
        val nombre = prefs.getString("nombre", "Usuario")
        val apellido = prefs.getString("apellido", "Inconocido")
        val email = prefs.getString("email", "Email no disponible")
        val perfil = prefs.getString("perfil", "Sin definir")

        val tvBienvenida = findViewById<TextView>(R.id.tvBienvenida)
        tvBienvenida.text = "Bienvenido, $nombre $apellido\nCorreo: $email\nPerfil de inversión: $perfil"
    }
}
