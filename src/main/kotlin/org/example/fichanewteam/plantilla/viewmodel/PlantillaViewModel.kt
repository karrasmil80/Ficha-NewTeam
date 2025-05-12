package org.example.fichanewteam.plantilla.viewmodel

import com.github.michaelbull.result.*
import org.example.fichanewteam.plantilla.service.PlantillaService
import org.example.fichanewteam.plantilla.storage.PlantillaStorage
import javafx.beans.property.SimpleObjectProperty


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
        TODO("Not yet implemented")
    }

    private fun loadPlantilla() {
        servicio.findAll().onSuccess {
            state.value = state.value.copy(plantilla = it)
            updateActualState()
        }
    }
    private fun updateActualState() {

    }

    data class PlantillaState()
}


