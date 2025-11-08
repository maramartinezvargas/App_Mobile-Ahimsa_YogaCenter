package com.example.ae1_pmdm_tamaramartinezvargas.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ae1_pmdm_tamaramartinezvargas.R

// Activity donde se muestra la confirmación de la reserva y permite compartirla
class ConfirmacionActivity : AppCompatActivity() {

    // Constante para identificar la solicitud de permiso de contactos
    private val PERMISSION_REQUEST_CONTACTS = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmacion)

        // Referencias a elementos de la UI
        val textFecha = findViewById<TextView>(R.id.textFecha)
        val textHora = findViewById<TextView>(R.id.textHora)
        val btnOk = findViewById<Button>(R.id.btnOk)
        val btnCompartir = findViewById<LinearLayout>(R.id.layoutCompartir)

        // Recuperar datos de la reserva enviados desde CalendarioActivity
        val fecha = intent.getStringExtra("fecha") ?: "Sin fecha"
        val hora = intent.getStringExtra("hora") ?: "Sin hora"

        // Mostrar fecha y hora en la interfaz
        textFecha.text = "Fecha: $fecha"
        textHora.text = "Hora: $hora"

        // Botón OK: vuelve al perfil del usuario
        btnOk.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
            finish() // Cerramos esta activity
        }

        // Botón Compartir: comprueba permiso antes de compartir
        btnCompartir.setOnClickListener {
            checkContactsPermission()
        }
    }

    // Comprueba si se tiene permiso para acceder a contactos
    private fun checkContactsPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Solicita el permiso al usuario
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_CONTACTS),
                PERMISSION_REQUEST_CONTACTS
            )
        } else {
            // Permiso ya concedido, se comparte la reserva
            shareReservation()
        }
    }

    // Maneja la respuesta del usuario a la solicitud de permisos
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CONTACTS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido
                shareReservation()
            } else {
                // Permiso denegado
                Toast.makeText(this, "Permiso de contactos denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para compartir la reserva mediante apps que acepten texto
    private fun shareReservation() {
        val fecha = findViewById<TextView>(R.id.textFecha).text
        val hora = findViewById<TextView>(R.id.textHora).text
        val message = "Reserva confirmada:\n$fecha\n$hora\n¡Te esperamos!"

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, message)
        startActivity(Intent.createChooser(intent, "Compartir reserva"))
    }
}
