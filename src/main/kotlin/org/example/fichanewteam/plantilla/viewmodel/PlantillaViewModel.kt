package org.example.fichanewteam.plantilla.viewmodel

import com.github.michaelbull.result.*
import com.github.michaelbull.result.onSuccess
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
        // variables de las posibles consultas
        state.value = state.value.copy(
            //variables = variables de las consultas
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
                    id = plantilla.id.toString(),
                    nombre = plantilla.nombre,
                    apellidos = plantilla.apellidos,
                    fechaNacimiento = plantilla.fechaNacimiento,
                    fechaIncorporacion = plantilla.fechaIncorporacion,
                    salario = plantilla.salario,
                    pais = plantilla.pais,
                    rol = plantilla.rol,
                    posicion = plantilla,
                    dorsal = plantilla,
                    altura: Double?,
                    peso: Double?,
                    goles: Int,
                    partidosJugados: Int,
                )
            )
            "Entrenador" -> state.value = state.value.copy(
                entrenador = EntrenadorState(

                )
            )
        }

    }

    enum class TipoFiltro(val value: String) {
        TODOS("Todos/as"), JUGADOR("Jugador: si"), ENTRENADOR("Entrenador: si")
    }

    data class ExpedienteState(
        val typesPlantilla: List<String> = emptyList(),
        val plantilla: List<Plantilla> = emptyList(),
        val jugador: List<Jugador> = emptyList(),
        val entrenador: List<Entrenador> = emptyList(),

        val persona: ExpedienteState = ExpedienteState(),
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


