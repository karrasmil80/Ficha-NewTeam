package org.example.fichanewteam.plantilla.storage

import com.github.michaelbull.result.Result
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.models.Plantilla
import java.io.File

interface PlantillaZipStorage {
    fun exportToZip(zipFile: File, data: List<Plantilla>): Result<File, PlantillaError>
    fun loadFromZip(unzipFile: File): Result<List<Plantilla>, PlantillaError>
}