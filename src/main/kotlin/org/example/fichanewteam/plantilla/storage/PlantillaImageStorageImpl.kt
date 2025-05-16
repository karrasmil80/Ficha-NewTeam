package org.example.fichanewteam.plantilla.storage

import com.github.michaelbull.result.Result
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.models.Plantilla
import java.io.File

class PlantillaImgStorageImpl : PlantillaImageStorage {
    override fun loadImage(imgName: String): Result<List<Plantilla>, PlantillaError> {
        TODO("Not yet implemented")
    }

    override fun saveImage(fileName: File): Result<File, PlantillaError> {
        TODO("Not yet implemented")
    }

    override fun deleteImage(fileName: String): Result<Unit, PlantillaError> {
        TODO("Not yet implemented")
    }

    override fun updateImage(imgName: String, newFileImg: File): Result<File, PlantillaError> {
        TODO("Not yet implemented")
    }

    override fun deleteAllImages(): Result<Long, PlantillaError> {
        TODO("Not yet implemented")
    }

}