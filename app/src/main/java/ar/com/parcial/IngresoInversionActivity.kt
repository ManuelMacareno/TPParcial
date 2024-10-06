package ar.com.parcial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class IngresoInversionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso_inversion)

        val btnGuardar = findViewById<Button>(R.id.btnGuardarInversion)

        btnGuardar.setOnClickListener {

            val monto1 = findViewById<EditText>(R.id.etMonto1).text.toString().toDoubleOrNull()
            val tasa1 = findViewById<EditText>(R.id.etTasa1).text.toString().toDoubleOrNull()
            val plazo1 = findViewById<EditText>(R.id.etPlazo1).text.toString().toIntOrNull()

            val monto2 = findViewById<EditText>(R.id.etMonto2).text.toString().toDoubleOrNull()
            val tasa2 = findViewById<EditText>(R.id.etTasa2).text.toString().toDoubleOrNull()
            val plazo2 = findViewById<EditText>(R.id.etPlazo2).text.toString().toIntOrNull()

            if (monto1 != null && tasa1 != null && plazo1 != null && monto2 != null && tasa2 != null && plazo2 != null) {

                val intent = Intent(this, CalculoResultadosActivity::class.java)
                intent.putExtra("monto1", monto1)
                intent.putExtra("tasa1", tasa1)
                intent.putExtra("plazo1", plazo1)
                intent.putExtra("monto2", monto2)
                intent.putExtra("tasa2", tasa2)
                intent.putExtra("plazo2", plazo2)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, ingrese todos los valores", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

