package com.example.ae1_pmdm_tamaramartinezvargas.ui

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ae1_pmdm_tamaramartinezvargas.R
import com.example.ae1_pmdm_tamaramartinezvargas.model.Reserva
import com.example.ae1_pmdm_tamaramartinezvargas.storage.PrefManager
import java.text.SimpleDateFormat
import java.util.*

// Activity principal donde el usuario selecciona fecha y hora para reservar una sesión
class CalendarioActivity : AppCompatActivity() {

    // Instancia de PrefManager para acceder a datos del usuario
    private lateinit var prefManager: PrefManager

    // Referencias a elementos de la interfaz
    private lateinit var tvNombreUsuario: TextView
    private lateinit var calendarView: CalendarView
    private lateinit var spinnerSesiones: Spinner
    private lateinit var btnReservar: Button
    private lateinit var btnVerPerfil: Button

    // Variables para manejar la fecha seleccionada
    private var fechaSeleccionada: String = ""
    private val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario)

        // Inicializar PrefManager
        prefManager = PrefManager(this)

        // Asociar elementos de la UI con las variables
        tvNombreUsuario = findViewById(R.id.tvNombreUsuario)
        calendarView = findViewById(R.id.calendarView)
        spinnerSesiones = findViewById(R.id.spinnerSesiones)
        btnReservar = findViewById(R.id.btnReservar)
        btnVerPerfil = findViewById(R.id.btnVerPerfil)

        // Mostrar el nombre del usuario (o "Usuario" si no hay)
        tvNombreUsuario.text = prefManager.getName() ?: "Usuario"

        // Configurar spinner con las horas disponibles para reservar
        val sesiones = listOf("10:00", "12:00", "16:00", "18:00", "20:00")
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, sesiones)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSesiones.adapter = adaptador

        // Inicializar fecha seleccionada con la fecha actual
        fechaSeleccionada = formatoFecha.format(Date())

        // Escuchar cambios en el calendario y actualizar la fecha seleccionada
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // month + 1 porque los meses en CalendarView son 0-indexados
            fechaSeleccionada = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year)
        }

        // Navegar a PerfilActivity al pulsar "Ver perfil"
        btnVerPerfil.setOnClickListener {
            startActivity(Intent(this, PerfilActivity::class.java))
        }

        // Crear reserva y pasarla a ConfirmacionActivity al pulsar "Reservar"
        btnReservar.setOnClickListener {
            val horaSeleccionada = spinnerSesiones.selectedItem.toString()
            val reserva = Reserva(fechaSeleccionada, horaSeleccionada)

            val intent = Intent(this, ConfirmacionActivity::class.java)
            intent.putExtra("fecha", reserva.fecha)
            intent.putExtra("hora", reserva.hora)
            startActivity(intent)
        }
    }
}
