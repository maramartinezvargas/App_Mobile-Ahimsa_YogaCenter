package com.example.ae1_pmdm_tamaramartinezvargas.model

// Clase que representa un usuario de la aplicación
// Se utiliza para almacenar la información del usuario registrada en la app
data class Usuario(
    val nombre: String,    // Nombre completo del usuario
    val email: String,     // Correo electrónico del usuario
    val telefono: String,  // Número de teléfono del usuario
    val nivel: String,     // Nivel del usuario (Principiante, Intermedio, Avanzado)
    val password: String   // Contraseña del usuario
)
