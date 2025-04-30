package org.example.fichanewteam.plantilla.service

import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.models.Plantilla
import com.github.michaelbull.result.Result

interface PlantillaService {
    fun findAll(): List<Plantilla>
    fun findById(id: Long): Result<Plantilla, PlantillaError>
    fun save(item : Plantilla): Result<Plantilla, PlantillaError>
    fun update(id: Long, item: Plantilla): Result<Plantilla, PlantillaError>
    fun delete(id: Long): Result<Plantilla, PlantillaError>
}