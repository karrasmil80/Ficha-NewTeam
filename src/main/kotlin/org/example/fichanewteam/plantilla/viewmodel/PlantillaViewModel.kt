package org.example.fichanewteam.plantilla.viewmodel

import com.github.michaelbull.result.*
import org.example.fichanewteam.plantilla.service.PlantillaService
import org.example.fichanewteam.plantilla.storage.PlantillaStorage
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.models.Plantilla
import org.example.fichanewteam.routes.RoutesManager
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.image.Image
import org.example.fichanewteam.plantilla.models.Entrenador
import org.example.fichanewteam.plantilla.models.Jugador
import java.io.File
import kotlin.String


class PlantillaViewModel(
    private val servicio: PlantillaService,
    private val storage: PlantillaStorage
) {
    val state: SimpleObjectProperty<ExpedienteState> = SimpleObjectProperty(ExpedienteState())

    init{
        loadPlantilla()
        loadTypes()
    }

    private fun loadTypes() {
        state.value = state.value.copy(typesPlantilla = TipoFiltro.entries.map { it.value })
    }

    private fun loadPlantilla() {
        servicio.findAll().onSuccess {
            state.value = state.value.copy(plantilla = it)
            updateActualState()
        }
    }
    private fun updateActualState() {
        //Consultas lucia
        val golesPromedio = state.value.jugador.count{ it.goles }
        val salarioMaximo = state.value.jugador.maxOf{ it.salario }
        val alturaMinima = state.value.jugador.minOf{ it.altura }
        val totalPartidos = state.value.jugador.sumOf{ it.partidosJugados }
        val totalJugadores = state.value.jugador.count{ it }

        //Consultas Pablo
        //ACUERDATE DE LAS INSTRUCIONES QUE TE HE DADO EN CLASE Y EN SU DEFECTO PREGUNTA
        /*
        val salarioPromedio
        val incorporacionAntigua
        val nacimientoActual
        val entrenadoresAsistentes
        val entrenadoresEspañoles
        */

        state.value = state.value.copy(
            golesPromedio = golesPromedio,
            salarioMaximo = salarioMaximo,
            alturaMinima = alturaMinima,
            totalPartidos = totalPartidos,
            totalJugadores = totalJugadores,

            persona = PlantillaState()
        )
    }

    fun plantillaFilteredList(tipo: String, nombre: String): List<Plantilla>{
        return TODO("Provide the return value")
    }

    fun savePlantillaToJson(file:File): Result<Long, PlantillaError> {
        return storage.storageDataJson(file, state.value.plantilla)
    }

    fun loadPlantillaJson(file: File, withImages: Boolean = false): Result<List<Plantilla>, PlantillaError> {
        return storage.deleteAllImages().andThen {
            storage.loadDataJson(file).onSuccess {
                servicio.deleteAll()
                servicio.saveAll(
                    if (withImages)
                        it
                    else
                        it.map{ a -> a.copy(id = Plantilla.NEW_ID, imagen = TipoImagen.SIN_IMAGEN.value) }
                )
                loadPlantilla()
            }
        }
    }

    fun updatePlantillaSelecionado(plantilla: Plantilla, jugador: Jugador, entrenador: Entrenador) {
        var imagen = Image(RoutesManager.getResourceAsStream("images/default_profile.png"))
        var fileImage = File(RoutesManager.getResource("images/default_profile.png").toURI())

        storage.loadImage(plantilla.rutaImagen).onSuccess {
            imagen = Image(it.absoluteFile.toURI().toString())
            fileImage = it
        }
        when(plantilla.rol){
            "Jugador" -> state.value = state.value.copy(
                jugador = JugadorState(
                    id = jugador.id.toString(),
                    nombre = jugador.nombre,
                    apellidos = jugador.apellidos,
                    fechaNacimiento = jugador.fechaNacimiento,
                    fechaIncorporacion = jugador.fechaIncorporacion,
                    salario = jugador.salario,
                    pais = jugador.pais,
                    rol = jugador.rol,
                    posicion = jugador.posicion,
                    dorsal = jugador.dorsal,
                    altura = jugador.altura,
                    peso = jugador.peso,
                    goles = jugador.goles,
                    partidosJugados = jugador.partidosJugados
                )
            )
            "Entrenador" -> state.value = state.value.copy(
                entrenador = EntrenadorState(
                    id = entrenador.id.toString(),
                    nombre = entrenador.nombre,
                    apellidos = entrenador.apellidos,
                    fechaNacimiento = entrenador.fechaNacimiento,
                    fechaIncorporacion = entrenador.fechaIncorporacion,
                    salario = entrenador.salario,
                    pais = entrenador.pais,
                    rol = entrenador.rol,
                    especialidad = entrenador.especialidad
                )
            )
        }
    }

    enum class TipoFiltro(val value: String) {
        TODOS("Todos/as"), JUGADOR("Jugador: si"), ENTRENADOR("Entrenador: si")
    }

    data class ExpedienteState(
        //Contenedores
        val typesPlantilla: List<String> = emptyList(),
        val plantilla: List<Plantilla> = emptyList(),
        val jugador: List<Jugador> = emptyList(),
        val entrenador: List<Entrenador> = emptyList(),

        //Variables de las consultas
        val golesPromedio: Int = 0,
        val salarioMaximo: Double = 0.00,
        val alturaMinima: Double = 0.00,
        val totalPartidos: Int = 0,
        val totalJugadores: Int = 0,

        //Persona hace referencia al conjunto es decir el individual de plantilla
        val persona: PlantillaState = PlantillaState()

    )

    data class PlantillaState(
        val id: String = "",
        val nombre: String = "",
        val apellidos: String = "",
        val fechaNacimiento: String = "",
        val fechaIncorporacion: String = "",
        val salario: Double? = 0.00,
        val pais: String = "",
        val rol: String = "",
        val rutaImagen: Image = Image(RoutesManager.getResourceAsStream("images/default_profile.png")),
        val fileImage: File? = null,
        val oldFileImage: File? = null,
    )

    data class JugadorState(
        val id: String,
        val nombre: String,
        val apellidos: String,
        val fechaNacimiento: String,
        val fechaIncorporacion: String,
        val salario: Double?,
        val pais: String,
        val rol: String,
        val posicion: String?,
        val dorsal: Int?,
        val altura: Double?,
        val peso: Double?,
        val goles: Int,
        val partidosJugados: Int,
        val rutaImagen: Image = Image(RoutesManager.getResourceAsStream("images/default_profile.png")),
        val fileImage: File? = null,
        val oldFileImage: File? = null,
    )

    data class EntrenadorState(
        val id: String,
        val nombre: String,
        val apellidos: String,
        val fechaNacimiento: String,
        val fechaIncorporacion: String,
        val salario: Double?,
        val pais: String,
        val rol: String,
        val especialidad: String,
        val rutaImagen: Image = Image(RoutesManager.getResourceAsStream("images/default_profile.png")),
        val fileImage: File? = null,
        val oldFileImage: File? = null,
    )
}