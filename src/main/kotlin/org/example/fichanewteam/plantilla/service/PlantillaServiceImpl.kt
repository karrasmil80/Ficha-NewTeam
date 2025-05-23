package org.example.fichanewteam.plantilla.service

import com.github.benmanes.caffeine.cache.Cache
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.models.Jugador
import org.example.fichanewteam.plantilla.repositories.PlantillaRepositoryImpl
import org.example.fichanewteam.plantilla.storage.FileFormat
import org.example.fichanewteam.plantilla.storage.PlantillaStorage
import org.example.fichanewteam.plantilla.storage.PlantillaStorageCsv
import org.example.fichanewteam.plantilla.storage.PlantillaStorageJson
import org.example.fichanewteam.plantilla.models.Plantilla
import org.lighthousegames.logging.logging
import java.io.File

class PlantillaServiceImpl (
    val repository: PlantillaRepositoryImpl,
    val storage : PlantillaStorage,
    val storageCsv: PlantillaStorageCsv,
    val storageJson: PlantillaStorageJson,
    private val cache : Cache<Long, Plantilla>
) : PlantillaService {

    private val logger = logging()
    //Función que devuelve una lista de los miembros de la plantilla
    override fun findAll(): Result<List<Jugador>, PlantillaError> {
        //logger.debug { "Obteniendo toda la plantilla" }
        //return repository.findAll()
        TODO()
    }

    //Función que busca a un miembro de la plantilla por id
    override fun findById(id: Long): Result<Plantilla, PlantillaError> {
        logger.debug { "Obteniendo por identificador : $id" }
        repository.findById(id)?.let {
            cache.put(id, it)
            Ok(it)
        } ?: Err(PlantillaError.PlantillaIdNotFound("Id no encontradaa : $id"))

        return Ok(repository.findById(id)!!)
    }

    //Funcion que guarda una entidad
    override fun save(item: Plantilla): Result<Plantilla, PlantillaError> {
        logger.debug { "Salvando miembro de la plantilla" }
        repository.save(item)?.id?.let {
            cache.put(it, item)
            Ok(it)
        }
        return Ok(repository.save(item))
    }

    //Funcion que actualiza el id de un miembro de la plantilla
    override fun update(id: Long, item: Plantilla): Result<Plantilla, PlantillaError> {
        logger.debug { "Actualizando miembro de la plantilla : $id" }
        repository.update(id, item)?.id?.let {
            cache.put(id, item)
            Ok(it)
        }
        return Ok(repository.update(id, item)!!)
    }

    //Función que borra el identificador de un miembro de la plantilla
    override fun deleteById(id: Long): Result<Unit, PlantillaError> {
        logger.debug { "deleteById" }
        repository.delete(id).also {
            cache.invalidate(id)
            return Ok(Unit) // no se si se puede

        }
    }

    //Función que lee un archivo con información de la plantilla y lo convierte en una lista de objetos
    override fun readFile(file: File, format: FileFormat): List<Plantilla> {
        logger.debug { "Leyendo el fichero..." }
        return when(format) {
            FileFormat.CSV -> storageCsv.readFile(file, format)
            FileFormat.JSON -> storageJson.readFile(file, format)
            else -> throw IllegalArgumentException("Format invalido")
        }
    }

    //Función que escribe una lista de objetos de plantilla en un archivo en el formato especificado.
    override fun writeFile(file: File, format: FileFormat, personal: List<Plantilla>) {
        return when(format) {
            FileFormat.JSON -> storageJson.writeFile(file, format, personal)
            else -> throw IllegalArgumentException("El formato no es compatible")
        }
    }

    //Función que elimina toda la informacion sobre un miembro de la plantilla
    override fun deleteAll(): Result<Unit, PlantillaError> {
        TODO("Not yet implemented")
    }

    //Función que guarda todos los items en una lista
    override fun saveAll(plantilla: List<Plantilla>): Result<List<Plantilla>, PlantillaError> {
        TODO("Not yet implemented")
    }
}
