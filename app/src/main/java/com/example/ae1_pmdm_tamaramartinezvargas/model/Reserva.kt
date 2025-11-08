package com.example.ae1_pmdm_tamaramartinezvargas.model

// Clase que representa una reserva de sesión de yoga
// Se usa como modelo de datos para almacenar la fecha y la hora de la reserva
data class Reserva(
    val fecha: String,  // Fecha de la reserva, en formato "dd/MM/yyyy"
    val hora: String    // Hora de la reserva, por ejemplo "16:00"
)
