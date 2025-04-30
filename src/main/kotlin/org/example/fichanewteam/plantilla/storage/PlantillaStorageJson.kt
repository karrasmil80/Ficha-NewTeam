package org.example.fichanewteam.plantilla.storage

import com.github.michaelbull.result.Err
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.fichanewteam.PersonalDto
import org.example.fichanewteam.models.Jugador
import org.example.fichanewteam.plantilla.dto.PersonalDto
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.mapper.toDto
import org.example.fichanewteam.plantilla.mapper.toEntrenador
import org.example.fichanewteam.plantilla.mapper.toJugador
import org.example.models.Entrenador
import org.example.models.Jugador
import org.example.models.Plantilla
import org.lighthousegames.logging.logging
import java.io.File

class PlantillaStorageJson : PersonalStorageFile {
    // implementacion del logger
    val logger = logging()

    init {
        logger.debug { "inicializando PersonalStorageJson" }
    }
    // Lee el archivo json y lo transforma a una cadena
    override fun readFile(file: File): List<Plantilla> {
        println()
        logger.debug { "Leyendo JSON" }

        if (!file.exists() || !file.isFile || !file.canRead()) {
            throw IllegalArgumentException("El fichero no se puede leer, no es un fichero o no se ha encontrado")
        } else {
            val json = Json { ignoreUnknownKeys = true; prettyPrint = true }
            val imprimirJson = file.readText()
            val listaPersonalDto = json.decodeFromString<List<PersonalDto>>(imprimirJson)

            val listaPersonalModel = listaPersonalDto.map {
                when (it.rol) {
                    "Entrenador" -> it.toEntrenador()
                    else -> it.toJugador()
                }
            }
            return listaPersonalModel
        }
    }

    // Escribe en el archivo json
    override fun writeFile(personal: List<Plantilla>, file: File): List<Plantilla> {
        if (!file.parentFile.exists() || !file.parentFile.isDirectory || !file.canWrite()) {
            Err(PlantillaError.PlantillaStorageError("El fichero json no se puede sobreescribir o no existe en su directorio padre"))
        } else {
            val json = Json { ignoreUnknownKeys = true; prettyPrint = true }
            val listaPersonalDto = personal.map {
                when (it) {
                    is Jugador -> { it.toDto() }
                    is Entrenador -> { it.toDto() }
                    else -> null
                }
            }

            val jsonString = json.encodeToString(listaPersonalDto)
            file.writeText(jsonString)
        }
        return personal
    }
}