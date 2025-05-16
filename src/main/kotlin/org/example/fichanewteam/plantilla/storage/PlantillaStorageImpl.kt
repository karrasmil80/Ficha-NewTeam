package org.example.fichanewteam.plantilla.storage

import com.github.michaelbull.result.Result
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.models.Plantilla
import java.io.File

class PlantillaStorageImpl : PlantillaStorage {
    override fun readFile(file: File, format: FileFormat): List<Plantilla> {
        TODO("Not yet implemented")
    }

    override fun writeFile(file: File, format: FileFormat, personal: List<Plantilla>) {
        TODO("Not yet implemented")
    }

    override fun storageDataJson(file: File, data: List<Plantilla>): Result<Long, PlantillaError> {
        TODO("Not yet implemented")
    }

    override fun deleteAllImages(): Result<Long, PlantillaError> {
        TODO("Not yet implemented")
    }

    override fun loadDataJson(file: File): Result<List<Plantilla>, PlantillaError> {
        TODO("Not yet implemented")
    }

    override fun loadImage(imagenName: String): Result<List<Plantilla>, PlantillaError> {
        TODO("Not yet implemented")
    }

    override fun saveImage(fileName: File): Result<File, PlantillaError> {
        TODO("Not yet implemented")
    }

    override fun deleteImage(fileName: String): Result<Unit, PlantillaError> {
        TODO("Not yet implemented")
    }

}