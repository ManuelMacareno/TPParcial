package ar.com.parcial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FormularioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApellido = findViewById<EditText>(R.id.etApellido)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)

        btnEnviar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val apellido = etApellido.text.toString().trim()
            val email = etEmail.text.toString().trim()

            if (nombre.isNotEmpty() && apellido.isNotEmpty() && email.isNotEmpty()) {

                val prefs = getSharedPreferences("TestInversorPrefs", Context.MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putString("nombre", nombre)
                editor.putString("apellido", apellido)
                editor.putString("email", email)
                editor.apply()

                val intent = Intent(this, TestInversorActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}







