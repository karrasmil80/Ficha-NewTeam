package org.example.fichanewteam.plantilla.service

import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.models.Plantilla
import com.github.michaelbull.result.Result
import org.example.fichanewteam.plantilla.storage.FileFormat
import java.io.File

//PARTE BUENA
interface PlantillaService {
    fun findAll(): List<Plantilla>
    fun findById(id: Long): Result<Plantilla, PlantillaError>
    fun save(item : Plantilla): Result<Plantilla, PlantillaError>
    fun update(id: Long, item: Plantilla): Result<Plantilla, PlantillaError>
    fun deleteById(id: Long): Result<Unit, PlantillaError>
    fun readFile(file: File, format: FileFormat): List<Plantilla>
    fun writeFile(file: File, format: FileFormat, personal: List<Plantilla>)
    fun deleteAll(): Result<Unit, PlantillaError>
    fun saveAll(plantilla: List<Plantilla>): Result<List<Plantilla>, PlantillaError>
}
//PARTE BUENA