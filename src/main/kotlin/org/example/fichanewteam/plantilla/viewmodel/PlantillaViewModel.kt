package org.example.fichanewteam.plantilla.viewmodel

import com.github.michaelbull.result.*
import org.example.fichanewteam.plantilla.service.PlantillaService
import org.example.fichanewteam.plantilla.storage.PlantillaStorage
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.models.Plantilla
import org.example.fichanewteam.routes.RoutesManager
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.image.Image
import java.io.File


class PlantillaViewModel(
    private val servicio: PlantillaService,
    private val storage: PlantillaStorage
) {
    val state: SimpleObjectProperty<PlantillaState> = SimpleObjectProperty(PlantillaState())

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

    fun updatePlantillaSelecionado(plantilla: Plantilla) {
        var imagen = Image(RoutesManager.getResourceAsStream("images/default_profile.png"))
        var fileImage = File(RoutesManager.getResource("images/default_profile.png").toURI())

        storage.loadImage(plantilla.rutaImagen).onSuccess {
            imagen = Image(it.absoluteFile.toURI().toString())
            fileImage = it as File
        }
    }

    enum class TipoFiltro(val value: String) {
        TODOS("Todos/as"), JUGADOR("Jugador: si"), ENTRENADOR("Entrenador: si")
    }

    data class PlantillaState(
        val typesPlantilla: List<String> = emptyList(),
        val plantilla: List<Plantilla> = emptyList(),

        val persona: PlantillaState = PlantillaState(),
    )
}


