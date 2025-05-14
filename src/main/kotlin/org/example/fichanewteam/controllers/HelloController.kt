package org.example.fichanewteam.controllers

import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import org.example.fichanewteam.plantilla.models.Entrenador
import org.example.fichanewteam.plantilla.models.Jugador
import org.example.fichanewteam.plantilla.models.Plantilla
import org.example.fichanewteam.routes.RoutesManager
import org.lighthousegames.logging.logging
import javafx.scene.input.KeyCode

import java.sql.DriverManager
import java.sql.PreparedStatement


private val logger = logging()
class HelloController {


    // Elementos de la interfaz para vincular con fx:id en el FXML
    @FXML
    private lateinit var modoEdicionToggle: ToggleButton

    @FXML
    private lateinit var menuHelp: MenuItem

    @FXML
    private lateinit var menuExportar: MenuItem

    @FXML
    private lateinit var menuImportar: MenuItem

    @FXML
    private lateinit var menuPegar: MenuItem

    @FXML
    private lateinit var menuCopiar: MenuItem

    @FXML
    private lateinit var menuSalir: MenuItem

    @FXML
    private lateinit var menuGuardar: MenuItem

    @FXML
    private lateinit var menuAbrir: MenuItem

    @FXML
    private lateinit var menuItem: MenuItem

    @FXML
    private lateinit var nuevoButton: Button

    @FXML
    private lateinit var editarButton: Button

    @FXML
    private lateinit var eliminarButton: Button

    @FXML
    private lateinit var exportarButton: Button

    @FXML
    private lateinit var importarButton: Button

    @FXML
    private lateinit var welcomeText: Label

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
    private lateinit var añadirButton: Button

    @FXML
    private lateinit var buttonCancelar: Button

    @FXML
    private lateinit var buttonGuardar: Button

    @FXML
    private lateinit var entrenadoresEspañolesField: TextField

    @FXML
    private lateinit var entrenadoresAsistentesField: TextField

    @FXML
    private lateinit var fechaActualField: TextField

    @FXML
    private lateinit var fechaAntiguaField: TextField

    @FXML
    private lateinit var salarioPromedioField: TextField

    @FXML
    private lateinit var NumJugadoresField: TextField

    @FXML
    private lateinit var PartidosTotalField: TextField

    @FXML
    private lateinit var AlturaMinimaField: TextField

    @FXML
    private lateinit var salarioMaximoField: TextField

    @FXML
    private lateinit var golePromedioField: TextField



    @FXML
    fun initialize() {
        initEvents()
        initDefaultValues()
    }

    fun initEvents() {
        logger.debug { "Iniciando eventos" }
        menuHelp.setOnAction { onHelpAction() }
        menuSalir.setOnAction { RoutesManager.onAppExit() }
        añadirButton.setOnAction { onAddMemberAction()}
        //modoEdicionToggle.setOnAction { onToggleViewAction() }
    }

    fun onHelpAction() {
        logger.debug { "onHelpAction" }
        RoutesManager.initHelpStage()
    }

    fun initDefaultValues() {
        logger.info { "Iniciando valores por defecto" }

        //Atajos del teclado
        menuCopiar.accelerator = KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN)
        menuGuardar.accelerator = KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN)
        menuPegar.accelerator = KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN)
        menuImportar.accelerator = KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN)
        menuExportar.accelerator = KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN)
        menuHelp.accelerator = KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN)

        //Variables desactivadas al inicio de la app para imposibilitar el editarlas
        buttonGuardar.isDisable = true
        buttonCancelar.isDisable = true
        buttonGuardar.isDisable = true
        buttonCancelar.isDisable = true
        paisField.isDisable = true
        fechaIncorporacionField.isDisable = true
        fechaNacimientoField.isDisable = true
        salarioField.isDisable = true
        apellidosField.isDisable = true
        nombreField.isDisable = true
        golesField.isDisable = true
        partidosField.isDisable = true
        dorsalField.isDisable = true
        alturaField.isDisable = true
        pesoField.isDisable = true
        rolComboBox.isDisable = true
        posicionComboBox.isDisable = true
        golePromedioField.isDisable = true
        salarioMaximoField.isDisable = true
        salarioPromedioField.isDisable = true
        AlturaMinimaField.isDisable = true
        PartidosTotalField.isDisable = true
        NumJugadoresField.isDisable = true
        fechaAntiguaField.isDisable = true
        fechaActualField.isDisable = true
        entrenadoresAsistentesField.isDisable = true
        entrenadoresEspañolesField.isDisable = true


        //Opciones de la comboBox
        val boxItemsRol = listOf("Jugador", "Entrenador")
        rolComboBox.items.addAll(boxItemsRol)

        val boxItemsPosicion = listOf("Defensa", "Centrocampista", "Delantero", "Portero")
        posicionComboBox.items.addAll(boxItemsPosicion)

        //Opciones de la comboBox de entrenador
        //Por hacer los campos comunes de entrenador (especialidad)

    }

    fun onAddMemberAction() {
        logger.debug { "onAddMemberAction" }
        RoutesManager.initEleccionaStage()
        buttonGuardar.isDisable = false
        buttonCancelar.isDisable = false
        paisField.isDisable = false
        fechaIncorporacionField.isDisable = false
        fechaNacimientoField.isDisable = false
        salarioField.isDisable = false
        apellidosField.isDisable = false
        nombreField.isDisable = false
        rolComboBox.isDisable = false
        posicionComboBox.isDisable = false

    }

    //Aquí irá lo que diferencia entre entrenador y jugador, es decir, cuando se seleccione en la comboBox de Rol la opcion jugador
    //Automaticamente se activarán los botones de dicho rol para poder salvarlos o editarlos
    fun onComboBoxAction() {

    }




}









/*
@FXML
private fun initialize() {
    // Código de inicialización
    welcomeText.text = "Bienvenido a New Team App"

    // Configurar el estado inicial del toggle y los botones
    modoEdicionToggle.isSelected = modoEdicion
    modoEdicionToggle.text = "Cambiar a Modo Lectura"
    actualizarEstadoBotones(modoEdicion)

    // Configurar las columnas de la tabla
    idColumn.cellValueFactory = PropertyValueFactory("id")
    nombreColumn.cellValueFactory = PropertyValueFactory("nombre")
    apellidosColumn.cellValueFactory = PropertyValueFactory("apellidos")
    rolColumn.cellValueFactory = PropertyValueFactory("rol")

    // Configurar ComboBoxes
    rolComboBox.items = FXCollections.observableArrayList("Jugador", "Entrenador")
    rolComboBox.selectionModel.selectFirst() // Primera por defecto

    val posiciones = listOf("DEFENSA", "CENTROCAMPISTA", "DELANTERO", "PORTERO", "NINGUNO")
    posicionComboBox.items = FXCollections.observableArrayList(posiciones)
    posicionComboBox.selectionModel.selectFirst() // Primera por defecto

    val especialidades = listOf("ENTRENADOR_PRINCIPAL", "ENTRENADOR_ASISTENTE", "ENTRENADOR_PORTEROS")
    especialidadComboBox.items = FXCollections.observableArrayList(especialidades)
    especialidadComboBox.selectionModel.selectFirst() // Primera por defecto

    // Cargar imagen por defecto
    val imageUrl = javaClass.getResource("/images/default_profile.png")?.toExternalForm()
    if (imageUrl != null) {
        photoImageView.image = Image(imageUrl)
    }

    // Listener para la selección de la tabla
    plantillaTable.selectionModel.selectedItemProperty().addListener { _, _, newSelection ->
        if (newSelection != null) {
            mostrarDetallesPersona(newSelection)
        }
    }

    cargarDatos()
}

@FXML
fun cambiarModo() {
    modoEdicion = modoEdicionToggle.isSelected

    // Actualizar texto del botón según el modo
    if (modoEdicion) {
        modoEdicionToggle.text = "Cambiar a Modo Lectura"
        statusLabel.text = "Modo edición activado"
    } else {
        modoEdicionToggle.text = "Cambiar a Modo Edición"
        statusLabel.text = "Modo solo lectura activado"
    }

    // Actualizar estado de los campos y botones
    actualizarEstadoCampos(modoEdicion)
    actualizarEstadoBotones(modoEdicion)
}

private fun actualizarEstadoBotones(editable: Boolean) {
    // Habilitar/deshabilitar botones de edición
    nuevoButton.isDisable = !editable
    editarButton.isDisable = !editable
    eliminarButton.isDisable = !editable

    // Los botones de importar/exportar siempre están disponibles
    importarButton.isDisable = false
    exportarButton.isDisable = false
}

private fun actualizarEstadoCampos(editable: Boolean) {
    // Campos de texto
    nombreField.isEditable = editable
    apellidosField.isEditable = editable
    salarioField.isEditable = editable
    paisField.isEditable = editable
    dorsalField.isEditable = editable
    alturaField.isEditable = editable
    pesoField.isEditable = editable
    golesField.isEditable = editable
    partidosField.isEditable = editable

    // Para que los campos no editables se vean normales (no grisados)
    val estilo = if (!editable) "-fx-opacity: 1.0" else ""
    nombreField.style = estilo
    apellidosField.style = estilo
    salarioField.style = estilo
    paisField.style = estilo
    dorsalField.style = estilo
    alturaField.style = estilo
    pesoField.style = estilo
    golesField.style = estilo
    partidosField.style = estilo

    // DatePickers
    fechaNacimientoField.isEditable = editable
    fechaIncorporacionField.isEditable = editable

    // ComboBoxes
    rolComboBox.isDisable = !editable
    posicionComboBox.isDisable = !editable
    especialidadComboBox.isDisable = !editable
}

companion object {
    private const val H2_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
    private const val H2_USER = "sa"
    private const val H2_PASS = ""
}

@FXML
fun handleExit() {
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
    val apellidos = apellidosField.text
    val fechaNacimiento = fechaNacimientoField.value
    val fechaIncorporacion = fechaIncorporacionField.value
    val salario = salarioField.text.toDoubleOrNull()
    val pais = paisField.text
    val rolSeleccionado = rolComboBox.value
    val posicionSeleccionada = posicionComboBox.value
    val dorsal = dorsalField.text.toIntOrNull()
    val altura = alturaField.text.toDoubleOrNull()
    val peso = pesoField.text.toDoubleOrNull()
    val goles = golesField.text.toIntOrNull() ?: 0
    val partidos = partidosField.text.toIntOrNull() ?: 0
    val especialidadSeleccionada = especialidadComboBox.value

    val rutaImagen = "/images/${apellidos.lowercase()}.png"

    when (rolSeleccionado) {
        "Jugador" -> {
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
                goles = goles,
                partidosJugados = partidos,
                rutaImagen = rutaImagen
            )
            guardarPlantillaEnH2(nuevoJugador)
            statusLabel.text = "Jugador guardado correctamente."
        }
        "Entrenador" -> {
            val nuevoEntrenador = Entrenador(
                nombre = nombre,
                apellidos = apellidos,
                fechaNacimiento = fechaNacimiento?.toString() ?: "",
                fechaIncorporacion = fechaIncorporacion?.toString() ?: "",
                salario = salario,
                pais = pais,
                especialidad = especialidadSeleccionada,
                rutaImagen = rutaImagen
            )
            guardarPlantillaEnH2(nuevoEntrenador)
            statusLabel.text = "Entrenador guardado correctamente."
        }
        else -> {
            statusLabel.text = "Selecciona un rol válido."
            return
        }
    }

    cargarDatos()
    limpiarCampos()
}

private fun limpiarCampos() {
    nombreField.clear()
    apellidosField.clear()
    fechaNacimientoField.value = null
    fechaIncorporacionField.value = null
    salarioField.clear()
    paisField.clear()
    dorsalField.clear()
    alturaField.clear()
    pesoField.clear()
    golesField.clear()
    partidosField.clear()
    rolComboBox.selectionModel.selectFirst()
    posicionComboBox.selectionModel.selectFirst()
    especialidadComboBox.selectionModel.selectFirst()
}

fun actualizarEstado(mensaje: String) {
    statusLabel.text = mensaje
}

*/


