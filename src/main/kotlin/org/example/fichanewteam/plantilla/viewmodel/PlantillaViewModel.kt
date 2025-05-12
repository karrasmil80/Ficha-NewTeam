package org.example.fichanewteam.plantilla.viewmodel

import com.github.michaelbull.result.*
import org.example.fichanewteam.plantilla.service.PlantillaService
import org.example.fichanewteam.plantilla.storage.PlantillaStorage
import javafx.beans.property.SimpleObjectProperty
import org.example.fichanewteam.plantilla.dto.PlantillaDto
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.models.Plantilla
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
        return storage.storage
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


