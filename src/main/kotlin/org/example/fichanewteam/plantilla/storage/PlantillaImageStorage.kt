package org.example.fichanewteam.plantilla.storage

import com.github.michaelbull.result.Result
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.models.Plantilla
import java.io.File

interface PlantillaImageStorage {
    fun loadImage(imgName: String): Result< File, PlantillaError>
    fun saveImage(fileName: File): Result<File, PlantillaError>
    fun deleteImage(fileName: File): Result<Unit, PlantillaError>
    fun updateImage(imgName: String, newFileImg: File): Result<File, PlantillaError>
    fun deleteAllImages(): Result<Long, PlantillaError>
}