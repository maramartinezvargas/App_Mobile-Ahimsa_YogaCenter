package com.example.ae1_pmdm_tamaramartinezvargas.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.ae1_pmdm_tamaramartinezvargas.R
import com.example.ae1_pmdm_tamaramartinezvargas.storage.PrefManager

/**
 * SplashActivity
 *
 * Pantalla inicial que se muestra al abrir la aplicación.
 * Su función es actuar como "decisor" del flujo de la app, mostrando un
 * breve tiempo de carga antes de redirigir al usuario según su estado de sesión.
 *
 * Flujo:
 * - Si el usuario ya tiene sesión iniciada, se dirige a CalendarioActivity.
 * - Si no, se dirige a LoginActivity.
 *
 * La actividad se cierra automáticamente para que el usuario no pueda volver
 * a ella con el botón "atrás".
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Simula carga de 2.5 segundos antes de decidir la siguiente pantalla
        Handler(Looper.getMainLooper()).postDelayed({
            val prefs = PrefManager(this)
            val nextActivity = if (prefs.isLoggedIn()) {
                // Usuario ya logueado → ir a CalendarioActivity
                Intent(this, CalendarioActivity::class.java)
            } else {
                // Usuario no logueado → ir a LoginActivity
                Intent(this, LoginActivity::class.java)
            }
            startActivity(nextActivity)
            finish() // Evita que el usuario regrese al splash con el botón atrás
        }, 2500)
    }
}
