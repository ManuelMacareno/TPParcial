package ar.com.parcial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val prefs = getSharedPreferences("TestInversorPrefs", Context.MODE_PRIVATE)
        val nombre = prefs.getString("nombre", "Usuario")
        val apellido = prefs.getString("apellido", "Inconocido")
        val email = prefs.getString("email", "Email no disponible")
        val perfil = prefs.getString("perfil", "Sin definir")

        val tvBienvenida = findViewById<TextView>(R.id.tvBienvenida)
        tvBienvenida.text = "Bienvenido, $nombre $apellido\nCorreo: $email\nPerfil de inversión: $perfil"

        val btnContinuar = findViewById<Button>(R.id.btnContinuar)
        btnContinuar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}







