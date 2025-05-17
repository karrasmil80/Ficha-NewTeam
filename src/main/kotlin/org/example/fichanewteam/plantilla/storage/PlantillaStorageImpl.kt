package org.example.fichanewteam.plantilla.storage

import com.github.michaelbull.result.Result
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.models.Plantilla
import java.io.File

class PlantillaStorageImpl (
    private val zipStorage: PlantillaZipStorage,
    private val imageStorage: PlantillaImageStorage,
    private val jsonStorage: PlantillaStorageJson
) : PlantillaStorage {
    override fun readFile(file: File, format: FileFormat): List<Plantilla> {
        return jsonStorage.readFile(file, format)
    }

    override fun writeFile(file: File, format: FileFormat, personal: List<Plantilla>) {
        jsonStorage.writeFile(file, format, personal)
    }

    override fun storageDataJson(file: File, data: List<Plantilla>): Result<Long, PlantillaError> {
        return jsonStorage.storageDataJson(file, data)
    }

    override fun loadDataJson(file: File): Result<List<Plantilla>, PlantillaError> {
        return jsonStorage.loadDataJson(file)
    }

    override fun deleteAllImages(): Result<Long, PlantillaError> {
        return imageStorage.deleteAllImages()
    }


    override fun loadImage(imagenName: String): Result<File, PlantillaError> {
        return imageStorage.loadImage(imagenName)
    }

    override fun saveImage(fileName: File): Result<File, PlantillaError> {
        return imageStorage.saveImage(fileName)
    }

    override fun deleteImage(fileName: String): Result<Unit, PlantillaError> {
        return imageStorage.deleteImage(File(fileName))
    }

    override fun exportToZip(zipFile: File, data: List<Plantilla>): Result<File, PlantillaError> {
        return zipStorage.exportToZip(zipFile, data)
    }

    override fun loadFromZip(unzipFile: File): Result<List<Plantilla>, PlantillaError> {
        return zipStorage.loadFromZip(unzipFile)
    }
}