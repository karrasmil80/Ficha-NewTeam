package org.example.fichanewteam.plantilla.service

import com.github.benmanes.caffeine.cache.Cache
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.repositories.PlantillaRepositoryImpl
import org.example.fichanewteam.plantilla.storage.FileFormat
import org.example.fichanewteam.plantilla.storage.PersonalStorage
import org.example.fichanewteam.plantilla.storage.PersonalStorageCsv
import org.example.fichanewteam.plantilla.storage.PersonalStorageJson
import org.example.fichanewteam.plantilla.models.Plantilla
import org.lighthousegames.logging.logging
import java.io.File

//PARTE BUENA
class PlantillaServiceImpl (
    val repository: PlantillaRepositoryImpl,
    val storage : PersonalStorage,
    val storageCsv: PersonalStorageCsv,
    val storageJson: PersonalStorageJson,
    private val cache : Cache<Long, Plantilla>
) : PlantillaService {

    private val logger = logging()
    override fun findAll(): List<Plantilla> {
        logger.debug { "Obteniendo toda la plantilla" }
        return repository.findAll()
    }

    override fun findById(id: Long): Result<Plantilla, PlantillaError> {
        logger.debug { "Obteniendo por identificador : $id" }
        repository.findById(id)?.let {
            cache.put(id, it)
            Ok(it)
        } ?: Err(PlantillaError.PlantillaIdNotFound("Id no encontradaa : $id"))

        return Ok(repository.findById(id)!!)
    }

    override fun save(item: Plantilla): Result<Plantilla, PlantillaError> {
        logger.debug { "Salvando miembro de la plantilla" }
        repository.save(item)?.id?.let {
            cache.put(it, item)
            Ok(it)
        }
        return Ok(repository.save(item))
    }

    override fun update(id: Long, item: Plantilla): Result<Plantilla, PlantillaError> {
        logger.debug { "Actualizando miembro de la plantilla : $id" }
        repository.update(id, item)?.id?.let {
            cache.put(id, item)
            Ok(it)
        }
        return Ok(repository.update(id, item)!!)
    }

    override fun delete(id: Long): Result<Plantilla, PlantillaError> {
        logger.debug { "Borrando miembro de la plantilla : $id" }
        return repository.delete(id)?.let {
            cache.invalidate(id)
            Ok(it)
        } ?: Err(PlantillaError.PlantillaIdNotFound("Id no encontradaa : $id"))
    }

    override fun readFile(file: File, format: FileFormat): List<Plantilla> {
        logger.debug { "Leyendo el fichero..." }
        return when(format) {
            FileFormat.CSV -> storageCsv.readFile(file, format)
            FileFormat.JSON -> storageJson.readFile(file, format)
            else -> throw IllegalArgumentException("Format invalido")
        }
    }

    override fun writeFile(file: File, format: FileFormat, personal: List<Plantilla>) {
        return when(format) {
            FileFormat.JSON -> storageJson.writeFile(file, format, personal)
            else -> throw IllegalArgumentException("El formato no es compatible")
        }
    }
}
//PARTE BUENA