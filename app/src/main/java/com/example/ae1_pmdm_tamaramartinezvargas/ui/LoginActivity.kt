package com.example.ae1_pmdm_tamaramartinezvargas.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ae1_pmdm_tamaramartinezvargas.R
import com.example.ae1_pmdm_tamaramartinezvargas.storage.PrefManager

// Activity que permite al usuario iniciar sesión con correo y contraseña
class LoginActivity : AppCompatActivity() {

    // Elementos de la interfaz
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var registerText: TextView
    private lateinit var forgotPasswordText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Referencias a los elementos de la UI
        emailInput = findViewById(R.id.inputEmail)
        passwordInput = findViewById(R.id.inputPassword)
        loginButton = findViewById(R.id.btnLogin)
        registerText = findViewById(R.id.textRegister)
        forgotPasswordText = findViewById(R.id.textForgotPassword)

        // Instancia de PrefManager para gestionar SharedPreferences
        val prefs = PrefManager(this)

        // Acción al pulsar el botón de login
        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            // Recupera credenciales guardadas
            val savedEmail = prefs.getEmail()
            val savedPassword = prefs.getPassword()

            if (email == savedEmail && password == savedPassword) {
                // Inicio de sesión correcto: marca sesión activa y va a CalendarioActivity
                prefs.setLoggedIn(true)
                Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, CalendarioActivity::class.java))
                finish()
            } else if (email == "" || password == "") {
                // Campos vacíos
                Toast.makeText(this, "Datos incompletos", Toast.LENGTH_SHORT).show()
            } else {
                // Credenciales incorrectas
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

        // Acción al pulsar "Registrarse": va a RegistroActivity
        registerText.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        // Acción al pulsar "¿Olvidaste tu contraseña?": actualmente no implementada
        forgotPasswordText.setOnClickListener {
            Toast.makeText(this, "Función no implementada", Toast.LENGTH_SHORT).show()
        }
    }
}
