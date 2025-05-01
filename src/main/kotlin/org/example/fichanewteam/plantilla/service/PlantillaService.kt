package org.example.fichanewteam.plantilla.service

import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.models.Plantilla
import com.github.michaelbull.result.Result
import org.example.fichanewteam.plantilla.storage.FileFormat
import java.io.File

//PARTE BUENA
interface PlantillaService {
    fun findAll(): List<Plantilla>
    fun findById(id: Long): Result<Plantilla, PlantillaError>
    fun save(item : Plantilla): Result<Plantilla, PlantillaError>
    fun update(id: Long, item: Plantilla): Result<Plantilla, PlantillaError>
    fun delete(id: Long): Result<Plantilla, PlantillaError>
    fun readFile(file: File, format: FileFormat): List<Plantilla>
    fun writeFile(file: File, format: FileFormat, personal: List<Plantilla>)
}
//PARTE BUENA