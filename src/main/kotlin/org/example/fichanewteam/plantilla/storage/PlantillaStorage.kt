package org.example.fichanewteam.plantilla.storage

import com.github.michaelbull.result.Result
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.models.Plantilla
import java.io.File

//PARTE BUENA
interface PlantillaStorage {
    fun readFile(file: File, format: FileFormat): List<Plantilla>
    fun writeFile(file: File, format: FileFormat, personal: List<Plantilla>)
    fun storageDataJson(file: File, data: List<Plantilla>): Result<Long, PlantillaError>
    fun deleteAllImages(): Result<Long, PlantillaError>
    fun loadDataJson(file: File): Result<Long, PlantillaError>
    fun loadImage(imagenName: String): Result<File, PlantillaError>
}
//PARTE BUENA