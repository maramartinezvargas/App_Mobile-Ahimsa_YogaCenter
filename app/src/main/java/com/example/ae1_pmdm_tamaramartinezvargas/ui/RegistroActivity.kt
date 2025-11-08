package com.example.ae1_pmdm_tamaramartinezvargas.ui

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ae1_pmdm_tamaramartinezvargas.R
import com.example.ae1_pmdm_tamaramartinezvargas.storage.PrefManager

/**
 * RegistroActivity
 *
 * Esta actividad permite a un nuevo usuario registrarse en la app.
 * Recoge los datos del usuario, valida que se hayan completado los campos y
 * que se acepten los términos, y los guarda mediante PrefManager en SharedPreferences.
 * Tras un registro exitoso, redirige al LoginActivity para iniciar sesión.
 */
class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Referencias a los elementos de la interfaz
        val inputName = findViewById<EditText>(R.id.inputName)
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val inputPhone = findViewById<EditText>(R.id.inputPhone)
        val spinnerLevel = findViewById<Spinner>(R.id.spinnerLevel)
        val inputPassword = findViewById<EditText>(R.id.inputPassword)
        val checkboxTerms = findViewById<CheckBox>(R.id.checkboxTerms)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // Instancia de PrefManager para guardar datos
        val prefs = PrefManager(this)

        // Configuración del Spinner de niveles
        val levels = listOf("Principiante", "Intermedio", "Avanzado")
        spinnerLevel.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, levels)

        // Listener del botón de registro
        btnRegister.setOnClickListener {
            val name = inputName.text.toString().trim()
            val email = inputEmail.text.toString().trim()
            val phone = inputPhone.text.toString().trim()
            val level = spinnerLevel.selectedItem.toString()
            val password = inputPassword.text.toString().trim()
            val accepted = checkboxTerms.isChecked

            // Validación de campos vacíos
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validación de aceptación de términos
            if (!accepted) {
                Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Guardar datos del usuario en SharedPreferences
            prefs.saveUser(name, email, phone, level, password)
            Toast.makeText(this, "Registro completado", Toast.LENGTH_SHORT).show()

            // Redirigir a LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
