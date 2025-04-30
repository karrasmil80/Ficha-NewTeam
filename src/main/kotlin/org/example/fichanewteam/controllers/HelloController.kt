package org.example.fichanewteam.controllers

import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.control.ComboBox
import javafx.scene.control.DatePicker
import javafx.scene.control.Dialog
import javafx.scene.control.Label
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import javafx.scene.layout.GridPane
import javafx.stage.Modality
import javafx.stage.Stage
import org.example.models.Entrenador
import org.example.fichanewteam.models.Jugador
import java.sql.DriverManager
import java.sql.Statement


class HelloController {
    // Elementos de la interfaz para vincular con fx:id en el FXML
    @FXML
    private lateinit var welcomeText: Label

    @FXML
    private lateinit var plantillaTable: TableView<Any>

    @FXML
    private lateinit var idColumn: TableColumn<Any, Any>

    @FXML
    private lateinit var nombreColumn: TableColumn<Any, String>

    @FXML
    private lateinit var apellidosColumn: TableColumn<Any, String>

    @FXML
    private lateinit var rolColumn: TableColumn<Any, String>

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
    private fun initialize() {
        // Código de inicialización
        welcomeText.text = "Bienvenido a New Team App"
    }

    // AcercaDe
    @FXML
    private fun handleAbout(event: ActionEvent) {
        try {
            // Cargar el archivo FXML de Acerca De
            val loader = FXMLLoader(javaClass.getResource("/views/acerca-de-view.fxml"))
            val root = loader.load<Parent>()

            // Crear una nueva escena
            val scene = Scene(root)

            // Crear un nuevo stage (ventana)
            val aboutStage = Stage()
            aboutStage.title = "Acerca De"
            aboutStage.scene = scene

            // Obtener la ventana principal (stage actual)
            val mainStage = (event.source as Node).scene.window as Stage

            // Configurar la modalidad para bloquear la ventana principal
            aboutStage.initOwner(mainStage)
            aboutStage.initModality(Modality.WINDOW_MODAL)

            // Mostrar la ventana y esperar hasta que se cierre
            aboutStage.showAndWait()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Manejador para el botón "Salir" del menú
    @FXML
    private fun handleSalir() {
        // Cerrar la aplicación
        Platform.exit()
    }

    // Otros métodos para manejar eventos de la interfaz
    @FXML
    private fun handleAnyadirPersona() {
        try {
            //Crear una ventana de diálogo para meter los datos
            val dialog = Dialog<Boolean>()
            dialog.title = "Añadir Persona"
            dialog.headerText = "Ingresa los datos de la nueva persona"

            // Configurar botones
            dialog.dialogPane.buttonTypes.addAll(ButtonType.OK, ButtonType.CANCEL)

            // Crear formulario
            val grid = GridPane()
            grid.hgap = 10.0
            grid.vgap = 10.0
            grid.padding = Insets(20.0, 150.0, 10.0, 10.0)

            nombreField.promptText = "Nombre"
            apellidosField.promptText = "Apellidos"
            rolComboBox.items.addAll("Jugador", "Entrenador")
            rolComboBox.selectionModel.selectFirst()

            grid.add(Label("Nombre:"), 0, 0)
            grid.add(nombreField, 1, 0)
            grid.add(Label("Apellidos:"), 0, 1)
            grid.add(apellidosField, 1, 1)
            grid.add(Label("Rol:"), 0, 2)
            grid.add(rolComboBox, 1, 2)

            dialog.dialogPane.content = grid

            // Dar foco al campo nombre
            Platform.runLater { nombreField.requestFocus() }

            // Convertir el resultado
            dialog.setResultConverter { buttonType ->
                if (buttonType == ButtonType.OK) {
                    // Validar que los campos no estén vacíos
                    if (nombreField.text.isNotEmpty() && apellidosField.text.isNotEmpty()) {
                        // Crear la nueva persona según el rol seleccionado
                        val nuevaPersona = when (rolComboBox.value) {
                            "Jugador" -> {
                                // Valores por defecto para campos numéricos
                                val dorsal = try {
                                    dorsalField.text.toInt()
                                } catch (e: Exception) {
                                    0
                                }
                                val altura = try {
                                    alturaField.text.toDouble()
                                } catch (e: Exception) {
                                    0.0
                                }
                                val peso = try {
                                    pesoField.text.toDouble()
                                } catch (e: Exception) {
                                    0.0
                                }
                                val goles = try {
                                    golesField.text.toInt()
                                } catch (e: Exception) {
                                    0
                                }
                                val partidos = try {
                                    partidosField.text.toInt()
                                } catch (e: Exception) {
                                    0
                                }

                                Jugador(
                                    id = 0,
                                    rol = "Jugador",
                                    nombre = nombreField.text,
                                    apellidos = apellidosField.text,
                                    fechaNacimiento = fechaNacimientoField.value.toString(),
                                    fechaIncorporacion = fechaIncorporacionField.value.toString(),
                                    salario = try {
                                        salarioField.text.toDouble()
                                    } catch (e: Exception) {
                                        0.0
                                    },
                                    pais = paisField.text,
                                    posicion = posicionComboBox.value,
                                    dorsal = dorsal,
                                    altura = altura,
                                    peso = peso,
                                    goles = goles,
                                    partidosJugados = partidos
                                )
                            }

                            "Entrenador" -> {
                                Entrenador(
                                    id = 0,
                                    rol = "Entrenador",
                                    nombre = nombreField.text,
                                    apellidos = apellidosField.text,
                                    fechaNacimiento = fechaNacimientoField.value.toString(),
                                    fechaIncorporacion = fechaIncorporacionField.value.toString(),
                                    salario = try {
                                        salarioField.text.toDouble()
                                    } catch (e: Exception) {
                                        0.0
                                    },
                                    pais = paisField.text,
                                    especialidad = especialidadComboBox.value
                                )
                            }

                            else -> null
                        }

                        // Guardar la persona en la base de datos
                        if (nuevaPersona != null) {
                            try {
                                // Establecer conexión con la base de datos
                                val connection = DriverManager.getConnection("jdbc:h2:mem:newteamh2", "root", "")

                                // Primero insertamos en la tabla plantilla
                                val stmtPlantilla = connection.prepareStatement(
                                    "INSERT INTO plantilla (nombre, apellidos, fecha_nacimiento, fecha_incorporacion, salario, pais, rol) " +
                                            "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
                                )

                                stmtPlantilla.setString(1, nuevaPersona.nombre)
                                stmtPlantilla.setString(2, nuevaPersona.apellidos)
                                stmtPlantilla.setDate(3, Date.valueOf(nuevaPersona.fechaNacimiento))
                                stmtPlantilla.setDate(4, Date.valueOf(nuevaPersona.fechaIncorporacion))
                                stmtPlantilla.setDouble(5, nuevaPersona.salario!!.toDouble())
                                stmtPlantilla.setString(6, nuevaPersona.pais)

                                // Determinar el rol según el tipo de objeto
                                when (nuevaPersona) {
                                    is Jugador -> stmtPlantilla.setString(7, "Jugador")
                                    is Entrenador -> stmtPlantilla.setString(7, "Entrenador")
                                    else -> throw Exception("Tipo de plantilla no reconocido")
                                }

                                // Ejecutar la inserción en la tabla plantilla
                                stmtPlantilla.executeUpdate()

                                // Obtener el ID generado
                                val rs = stmtPlantilla.generatedKeys
                                if (rs.next()) {
                                    val personaId = rs.getLong(1)

                                    // Ahora insertamos en la tabla específica según el tipo
                                    when (nuevaPersona) {
                                        is Jugador -> {
                                            val stmtJugador = connection.prepareStatement(
                                                "INSERT INTO jugadores (id, posicion, dorsal, altura, peso, goles, partidos_jugados) " +
                                                        "VALUES (?, ?, ?, ?, ?, ?, ?)"
                                            )
                                            stmtJugador.setLong(1, personaId)
                                            stmtJugador.setString(2, nuevaPersona.posicion ?: "")
                                            stmtJugador.setInt(3, nuevaPersona.dorsal ?: 0)  // Usa 0 como valor por defecto
                                            stmtJugador.setDouble(4, nuevaPersona.altura ?: 0.0)  // Usa 0.0 como valor por defecto
                                            stmtJugador.setDouble(5, nuevaPersona.peso ?: 0.0)  // Usa 0.0 como valor por defecto
                                            stmtJugador.setInt(6, nuevaPersona.goles ?: 0)
                                            stmtJugador.setInt(7, nuevaPersona.partidosJugados ?: 0)
                                            stmtJugador.executeUpdate()
                                            stmtJugador.close()
                                        }

                                        is Entrenador -> {
                                            val stmtEntrenador = connection.prepareStatement(
                                                "INSERT INTO entrenadores (id, especialidad) VALUES (?, ?)"
                                            )
                                            stmtEntrenador.setLong(1, personaId)
                                            stmtEntrenador.setString(2, nuevaPersona.especialidad)
                                            stmtEntrenador.executeUpdate()
                                            stmtEntrenador.close()
                                        }
                                    }
                                }

                                // Cerrar recursos
                                rs.close()
                                stmtPlantilla.close()
                                connection.close()

                                // Añadir la persona a la TableView
                                plantillaTable.items.add(nuevaPersona)

                                // Mostrar mensaje de éxito
                                val alert = Alert(Alert.AlertType.INFORMATION)
                                alert.title = "Persona añadida"
                                alert.headerText = null
                                alert.contentText = "La persona ha sido añadida correctamente a la base de datos."
                                alert.showAndWait()

                                return@setResultConverter true
                            } catch (e: Exception) {
                                e.printStackTrace()

                                // Mostrar mensaje de error
                                val alert = Alert(Alert.AlertType.ERROR)
                                alert.title = "Error"
                                alert.headerText = null
                                alert.contentText = "Error al guardar la persona en la base de datos: ${e.message}"
                                alert.showAndWait()

                                return@setResultConverter false
                            }
                        }
                    }
                }
                null
            }
            // Mostrar diálogo y esperar al resultado
            dialog.showAndWait()
        } catch (e: Exception) {
            e.printStackTrace()

            // Mostrar mensaje de error
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "Error"
            alert.headerText = null
            alert.contentText = "Ha ocurrido un error al añadir la persona: ${e.message}"
            alert.showAndWait()
        }
    }

    @FXML
    fun handleEditarPersona() {
        // Código para editar una persona seleccionada
    }

    @FXML
    fun handleBorrarPersona() {
        // Código para eliminar una persona seleccionada
    }
}