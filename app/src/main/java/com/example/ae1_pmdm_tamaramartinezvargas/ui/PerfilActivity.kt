package com.example.ae1_pmdm_tamaramartinezvargas.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ae1_pmdm_tamaramartinezvargas.R
import com.example.ae1_pmdm_tamaramartinezvargas.storage.PrefManager

// Activity que muestra la información del usuario y permite cerrar sesión
class PerfilActivity : AppCompatActivity() {

    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // Instancia de PrefManager para obtener datos del usuario y estado de sesión
        prefManager = PrefManager(this)

        // Referencias a los elementos de la UI
        val ivFotoPerfil = findViewById<ImageView>(R.id.ivFotoPerfil)
        val tvNombre = findViewById<TextView>(R.id.tvNombreUsuario)
        val tvCorreo = findViewById<TextView>(R.id.tvCorreoUsuario)
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)

        // Mostrar datos del usuario almacenados en SharedPreferences
        tvNombre.text = prefManager.getName() ?: "Usuario" // valor por defecto si es null
        tvCorreo.text = prefManager.getEmail() ?: "correo@ejemplo.com"

        // Listener para el botón "Cerrar sesión"
        btnCerrarSesion.setOnClickListener {
            prefManager.setLoggedIn(false) // marcar sesión como cerrada, sin borrar datos
            // Reinicia la aplicación enviando al SplashActivity y borrando historial de actividades
            val intent = Intent(this, SplashActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
