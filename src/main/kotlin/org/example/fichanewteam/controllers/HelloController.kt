package org.example.fichanewteam.controllers

import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import org.example.fichanewteam.plantilla.models.Entrenador
import org.example.fichanewteam.plantilla.models.Jugador
import org.example.fichanewteam.plantilla.models.Plantilla
import java.sql.DriverManager
import java.sql.PreparedStatement

class HelloController {
    // Elementos de la interfaz para vincular con fx:id en el FXML
    @FXML
    private lateinit var welcomeText: Label

    @FXML
    private lateinit var handleExit: Button

    @FXML
    private lateinit var plantillaTable: TableView<Plantilla>

    @FXML
    private lateinit var idColumn: TableColumn<Plantilla, Long>

    @FXML
    private lateinit var nombreColumn: TableColumn<Plantilla, String>

    @FXML
    private lateinit var apellidosColumn: TableColumn<Plantilla, String>

    @FXML
    private lateinit var rolColumn: TableColumn<Plantilla, String>

    @FXML
    private lateinit var nombreField: TextField

    @FXML
    private lateinit var apellidosField: TextField

    @FXML
    private lateinit var fechaNacimientoField: DatePicker

    @FXML
    private lateinit var fechaIncorporacionField: DatePicker

    @FXML
    private lateinit var salarioField: TextField

    @FXML
    private lateinit var paisField: TextField

    @FXML
    private lateinit var rolComboBox: ComboBox<String>

    @FXML
    private lateinit var posicionComboBox: ComboBox<String>

    @FXML
    private lateinit var dorsalField: TextField

    @FXML
    private lateinit var alturaField: TextField

    @FXML
    private lateinit var pesoField: TextField

    @FXML
    private lateinit var golesField: TextField

    @FXML
    private lateinit var partidosField: TextField

    @FXML
    private lateinit var especialidadComboBox: ComboBox<String>

    @FXML
    private lateinit var statusLabel: Label

    @FXML
    private lateinit var photoImageView: ImageView

    @FXML
    private lateinit var nombreCompletoLabel: Label

    @FXML
    private lateinit var rolPosicionLabel: Label

    @FXML
    private lateinit var paisLabel: Label

    @FXML
    private lateinit var incorporacionLabel: Label

    @FXML
    private lateinit var edadLabel: Label



    @FXML
    private fun initialize() {
        // Código de inicialización
        welcomeText.text = "Bienvenido a New Team App"

        idColumn.cellValueFactory = PropertyValueFactory("id")
        nombreColumn.cellValueFactory = PropertyValueFactory("nombre")
        apellidosColumn.cellValueFactory = PropertyValueFactory("apellidos")
        rolColumn.cellValueFactory = PropertyValueFactory("rol")
        rolComboBox.items = FXCollections.observableArrayList("Jugador", "Entrenador")
        rolComboBox.selectionModel.selectFirst() // Primera por defecto
        val posiciones = listOf("DEFENSA", "CENTROCAMPISTA", "DELANTERO", "PORTERO", "NINGUNO")
        posicionComboBox.items = FXCollections.observableArrayList(posiciones)
        posicionComboBox.selectionModel.selectFirst() // Primera por defecto
        val especialidades = listOf("ENTRENADOR_PRINCIPAL", "ENTRENADOR_ASISTENTE", "ENTRENADOR_PORTEROS")
        especialidadComboBox.items = FXCollections.observableArrayList(especialidades)
        especialidadComboBox.selectionModel.selectFirst() // Primera por defecto
        val imageUrl = javaClass.getResource("/images/default_profile.png")?.toExternalForm()
        if (imageUrl != null) {
            photoImageView.image = Image(imageUrl)
        }
        plantillaTable.selectionModel.selectedItemProperty().addListener { _, _, newSelection ->
            if (newSelection != null) {
                mostrarDetallesPersona(newSelection)
            }
        }

        cargarDatos()
    }

    companion object {
        private const val H2_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
        private const val H2_USER = "sa"
        private const val H2_PASS = ""
    }

    @FXML
    private fun handleExit() {
        Platform.exit()
    }

    @FXML
    fun handleAbout() {
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.title = "Acerca de"
        alert.headerText = "New Team App"
        alert.contentText = "Aplicación de gestión de plantilla de equipo.\nDesarrollada en Kotlin y JavaFX.\nCurso DAW 2024/2025."
        alert.showAndWait()
    }

    private fun cargarDatos() {
        val lista = obtenerPlantillaDesdeH2()
        plantillaTable.items = FXCollections.observableArrayList(lista)
    }

    private fun obtenerPlantillaDesdeH2(): List<Plantilla> {
        val lista = mutableListOf<Plantilla>()
        val connection = DriverManager.getConnection(H2_URL, H2_USER, H2_PASS)

        // Consulta para jugadores
        val rsJugadores = connection.createStatement().executeQuery("SELECT * FROM jugadores JOIN plantilla ON jugadores.id = plantilla.id")
        while (rsJugadores.next()) {
            lista.add(
                Jugador(
                    id = rsJugadores.getLong("id"),
                    nombre = rsJugadores.getString("nombre"),
                    apellidos = rsJugadores.getString("apellidos"),
                    fechaNacimiento = rsJugadores.getString("fecha_nacimiento"),
                    fechaIncorporacion = rsJugadores.getString("fecha_incorporacion"),
                    salario = rsJugadores.getDouble("salario"),
                    pais = rsJugadores.getString("pais"),
                    rol = rsJugadores.getString("rol"),
                    posicion = rsJugadores.getString("posicion"),
                    dorsal = rsJugadores.getInt("dorsal"),
                    altura = rsJugadores.getDouble("altura"),
                    peso = rsJugadores.getDouble("peso"),
                    goles = rsJugadores.getInt("goles"),
                    partidosJugados = rsJugadores.getInt("partidos_jugados"),
                    rutaImagen = rsJugadores.getString("ruta_imagen")
                )
            )
        }
        rsJugadores.close()

        // Consulta para entrenadores
        val rsEntrenadores = connection.createStatement().executeQuery("SELECT * FROM entrenadores JOIN plantilla ON entrenadores.id = plantilla.id")
        while (rsEntrenadores.next()) {
            lista.add(
                Entrenador(
                    id = rsEntrenadores.getLong("id"),
                    nombre = rsEntrenadores.getString("nombre"),
                    apellidos = rsEntrenadores.getString("apellidos"),
                    fechaNacimiento = rsEntrenadores.getString("fecha_nacimiento"),
                    fechaIncorporacion = rsEntrenadores.getString("fecha_incorporacion"),
                    salario = rsEntrenadores.getDouble("salario"),
                    pais = rsEntrenadores.getString("pais"),
                    rol = rsEntrenadores.getString("rol"),
                    especialidad = rsEntrenadores.getString("especialidad"),
                    rutaImagen = rsEntrenadores.getString("ruta_imagen")
                )
            )
        }
        rsEntrenadores.close()
        connection.close()
        return lista
    }

    private fun guardarPlantillaEnH2(plantilla: Plantilla) {
        val connection = DriverManager.getConnection(H2_URL, H2_USER, H2_PASS)

        // 1. Insertar en la tabla personal (común)
        val stmtPersonal: PreparedStatement = connection.prepareStatement(
            "INSERT INTO plantilla (nombre, apellidos, fecha_nacimiento, fecha_incorporacion, salario, pais, rol, ruta_imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
            PreparedStatement.RETURN_GENERATED_KEYS
        )
        stmtPersonal.setString(1, plantilla.nombre)
        stmtPersonal.setString(2, plantilla.apellidos)
        stmtPersonal.setString(3, plantilla.fechaNacimiento)
        stmtPersonal.setString(4, plantilla.fechaIncorporacion)
        stmtPersonal.setDouble(5, plantilla.salario ?: 0.0)
        stmtPersonal.setString(6, plantilla.pais)
        stmtPersonal.setString(7, plantilla.rol)
        stmtPersonal.setString(8, plantilla.rutaImagen)

        stmtPersonal.executeUpdate()

        // Obtener el ID generado
        val rs = stmtPersonal.generatedKeys
        var personaId: Long = 0
        if (rs.next()) {
            personaId = rs.getLong(1)
        }
        rs.close()
        stmtPersonal.close()

        // 2. Insertar en la tabla específica según el tipo
        when (plantilla) {
            is Jugador -> {
                val stmtJugador = connection.prepareStatement(
                    "INSERT INTO jugadores (id, posicion, dorsal, altura, peso, goles, partidos_jugados) VALUES (?, ?, ?, ?, ?, ?, ?)"
                )
                stmtJugador.setLong(1, personaId)
                stmtJugador.setString(2, plantilla.posicion ?: "")
                stmtJugador.setInt(3, plantilla.dorsal ?: 0)
                stmtJugador.setDouble(4, plantilla.altura ?: 0.0)
                stmtJugador.setDouble(5, plantilla.peso ?: 0.0)
                stmtJugador.setInt(6, plantilla.goles)
                stmtJugador.setInt(7, plantilla.partidosJugados)
                stmtJugador.executeUpdate()
                stmtJugador.close()
            }
            is Entrenador -> {
                val stmtEntrenador = connection.prepareStatement(
                    "INSERT INTO entrenadores (id, especialidad) VALUES (?, ?)"
                )
                stmtEntrenador.setLong(1, personaId)
                stmtEntrenador.setString(2, plantilla.especialidad)
                stmtEntrenador.executeUpdate()
                stmtEntrenador.close()
            }
        }

        connection.close()
    }

    private fun mostrarDetallesPersona(persona: Plantilla) {
        // Actualizar nombre completo
        nombreCompletoLabel.text = "${persona.nombre} ${persona.apellidos}"

        // Actualizar rol/posición
        when (persona) {
            is Jugador -> {
                rolPosicionLabel.text = "${persona.rol} / ${persona.posicion ?: "N/A"}"
            }
            is Entrenador -> {
                rolPosicionLabel.text = "${persona.rol} / ${persona.especialidad}"
            }
            else -> {
                rolPosicionLabel.text = persona.rol
            }
        }

        // Actualizar país, edad, etc.
        paisLabel.text = persona.pais
        try {
            // Asumimos que la fecha viene en formato 'YYYY-MM-DD'
            val partesFecha = persona.fechaNacimiento.split("-")
            if (partesFecha.size == 3) {
                val anioNacimiento = partesFecha[0].toInt()
                val mesNacimiento = partesFecha[1].toInt()
                val diaNacimiento = partesFecha[2].toInt()

                val hoy = java.time.LocalDate.now()
                var edad = hoy.year - anioNacimiento

                // Ajustar si todavía no ha cumplido años este año
                if (hoy.monthValue < mesNacimiento ||
                    (hoy.monthValue == mesNacimiento && hoy.dayOfMonth < diaNacimiento)) {
                    edad--
                }

                edadLabel.text = "$edad años"
            } else {
                edadLabel.text = "Edad desconocida"
            }
        } catch (e: Exception) {
            edadLabel.text = "Edad desconocida"
            println("Error al calcular la edad: ${e.message}")
        }
        incorporacionLabel.text = persona.fechaIncorporacion

        // Cargar imagen específica para el jugador o entrenador
        val imageUrl = if (persona.rutaImagen.isNotEmpty()) {
            // Si tiene una ruta de imagen personalizada
            javaClass.getResource(persona.rutaImagen)?.toExternalForm()
        } else {
            // Si no tiene imagen personalizada, usar una predeterminada según el tipo
            when (persona) {
                is Jugador -> "/images/jugadores/default_jugador.png"
                is Entrenador -> "/images/entrenadores/default_entrenador.png"
                else -> "/images/default_profile.png"
            }
        }

        if (imageUrl != null) {
            photoImageView.image = Image(imageUrl)
        } else {
            // Si no se encuentra la imagen, usar la predeterminada
            val defaultImageUrl = javaClass.getResource("/images/default_profile.png")?.toExternalForm()
            if (defaultImageUrl != null) {
                photoImageView.image = Image(defaultImageUrl)
            }
        }
    }


    @FXML
    fun onGuardarClicked() {
        val nombre = nombreField.text
        println("El nombre introducido es: $nombre")
        val apellidos = apellidosField.text
        println("Los apellidos introducidos son: $apellidos")
        val fechaNacimiento = fechaNacimientoField.value
        println("La fecha de nacimiento seleccionada es: $fechaNacimiento")
        val fechaIncorporacion = fechaIncorporacionField.value
        println("La fecha de incorporacion seleccionada es: $fechaIncorporacion")
        val salarioTexto = salarioField.text
        val salario: Double? = salarioTexto.toDoubleOrNull()
        println("El salario introducido es: $salario")
        val pais = paisField.text
        println("El pais introducido es: $pais")
        val rolSeleccionado = rolComboBox.value
        println("Rol seleccionado: $rolSeleccionado")
        val posicionSeleccionada = posicionComboBox.value
        println("Posicion seleccionada: $posicionSeleccionada")
        val dorsalTexto = dorsalField.text
        val dorsal: Int? = dorsalTexto.toIntOrNull()
        println("El dorsal introducido es: $dorsal")
        val alturaTexto = alturaField.text
        val altura: Double? = alturaTexto.toDoubleOrNull()
        println("La altura introducida es: $altura")
        val pesoTexto = pesoField.text
        val peso: Double? = pesoTexto.toDoubleOrNull()
        println("El peso introducido es: $peso")
        val golesTexto = golesField.text
        val goles: Int? = golesTexto.toIntOrNull()
        println("Los goles introducidos son: $goles")
        val partidosTexto = partidosField.text
        val partidos: Int? = partidosTexto.toIntOrNull()
        println("Los partidos jugados introducidos son: $partidos")
        val especialidadSeleccionada = especialidadComboBox.value
        println("Especialidad seleccionada: $especialidadSeleccionada")

        val rutaImagen = "/images/${apellidos.lowercase()}.png"

        if (rolSeleccionado == "Jugador") {
            val nuevoJugador = Jugador(
                nombre = nombre,
                apellidos = apellidos,
                fechaNacimiento = fechaNacimiento?.toString() ?: "",
                fechaIncorporacion = fechaIncorporacion?.toString() ?: "",
                salario = salario,
                pais = pais,
                posicion = posicionSeleccionada,
                dorsal = dorsal,
                altura = altura,
                peso = peso,
                goles = goles ?: 0,
                partidosJugados = partidos ?: 0,
                rutaImagen = rutaImagen // Añadir la ruta de imagen
            )
            guardarPlantillaEnH2(nuevoJugador)
            statusLabel.text = "Jugador guardado correctamente."
        } else if (rolSeleccionado == "Entrenador") {
            val nuevoEntrenador = Entrenador(
                nombre = nombre,
                apellidos = apellidos,
                fechaNacimiento = fechaNacimiento?.toString() ?: "",
                fechaIncorporacion = fechaIncorporacion?.toString() ?: "",
                salario = salario,
                pais = pais,
                especialidad = especialidadSeleccionada,
                rutaImagen = rutaImagen // Añadir la ruta de imagen
            )
            guardarPlantillaEnH2(nuevoEntrenador)
            statusLabel.text = "Entrenador guardado correctamente."
        } else {
            statusLabel.text = "Selecciona un rol válido."
            return
        }

        cargarDatos()
    }

    fun actualizarEstado(mensaje: String) {
        statusLabel.text = mensaje
    }
}