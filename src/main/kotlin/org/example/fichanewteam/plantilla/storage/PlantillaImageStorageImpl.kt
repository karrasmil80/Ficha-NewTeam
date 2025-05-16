package org.example.fichanewteam.plantilla.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.fichanewteam.config.Config
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.models.Plantilla
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.time.Instant

class PlantillaImgStorageImpl(
    private val config : Config
) : PlantillaImageStorage {

    private fun getImagenName(newFileImage: File): String {
        val name = newFileImage.name
        val extension = name.substring(name.lastIndexOf(".") + 1)
        return "${Instant.now().toEpochMilli()}.$extension"
    }

    override fun loadImage(imgName: String): Result<File, PlantillaError> {
        val file = File(config.imagesDirectory + imgName)
        return if (file.exists()) {
            Ok(file)
        } else {
            Err(PlantillaError.PlantillaStorageError("La imagen no existe: ${file.name}"))
        }
    }

    override fun saveImage(fileName: File): Result<File, PlantillaError> {
        return try {
            val newImage = File(config.imagesDirectory + getImagenName(fileName))
            Files.copy(fileName.toPath(), newImage.toPath(), StandardCopyOption.REPLACE_EXISTING)
            Ok(newImage)
        } catch (e: Exception) {
            Err(PlantillaError.PlantillaStorageError("La imagen no se puede guardar: ${fileName.name}"))
        }
    }

    override fun deleteImage(fileName: File): Result<Unit, PlantillaError> {
        Files.deleteIfExists(fileName.toPath())
        return Ok(Unit)
    }

    override fun updateImage(imgName: String, newFileImg: File): Result<File, PlantillaError> {
        return try {
            val newImage = File(config.imagesDirectory + imgName)
            Files.copy(newFileImg.toPath(), newImage.toPath(), StandardCopyOption.REPLACE_EXISTING)
            Ok(newImage)
        } catch (e: Exception) {
            Err(PlantillaError.PlantillaStorageError("La imagen no se puede actualizar: ${newFileImg.name}"))
        }
    }

    override fun deleteAllImages(): Result<Long, PlantillaError> {
        return try {
            Ok(
                Files.walk(Paths.get(config.imagesDirectory)).filter {
                    Files.isRegularFile(it)
                }.map { Files.deleteIfExists(it) }
                    .count())
        } catch (e: Exception) {
            Err(PlantillaError.PlantillaStorageError("No se ha podido eliminar la imagen: ${e.message}"))
        }

    }

}