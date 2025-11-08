package com.example.ae1_pmdm_tamaramartinezvargas.storage

import android.content.Context
import android.content.SharedPreferences

// Clase encargada de gestionar la persistencia de datos del usuario
// Utiliza SharedPreferences para guardar y recuperar información localmente
class PrefManager(context: Context) {

    // Instancia de SharedPreferences con nombre "ahimsa_prefs" en modo privado
    private val prefs: SharedPreferences =
        context.getSharedPreferences("ahimsa_prefs", Context.MODE_PRIVATE)

    // Guardar los datos del usuario en SharedPreferences
    fun saveUser(name: String, email: String, phone: String, level: String, password: String) {
        prefs.edit().apply {
            putString("name", name)       // Guardar nombre
            putString("email", email)     // Guardar correo electrónico
            putString("phone", phone)     // Guardar teléfono
            putString("level", level)     // Guardar nivel
            putString("password", password) // Guardar contraseña
            apply()                       // Confirmar los cambios
        }
    }

    // Métodos para obtener los datos del usuario
    fun getName(): String? = prefs.getString("name", null)
    fun getEmail(): String? = prefs.getString("email", null)
    fun getPhone(): String? = prefs.getString("phone", null)
    fun getLevel(): String? = prefs.getString("level", null)
    fun getPassword(): String? = prefs.getString("password", null)

    // Guardar el estado de sesión del usuario
    fun setLoggedIn(loggedIn: Boolean) {
        prefs.edit().putBoolean("isLoggedIn", loggedIn).apply()
    }

    // Comprobar si el usuario está logueado
    fun isLoggedIn(): Boolean = prefs.getBoolean("isLoggedIn", false)

    // Cerrar sesión: cambia solo el estado de login, no borra las credenciales
    fun logout() {
        setLoggedIn(false)
    }

    // Limpiar todos los datos guardados (usuario y estado de sesión)
    fun clear() {
        prefs.edit().clear().apply()
    }
}
