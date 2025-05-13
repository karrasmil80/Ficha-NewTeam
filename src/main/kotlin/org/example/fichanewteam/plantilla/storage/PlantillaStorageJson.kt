package org.example.fichanewteam.plantilla.storage

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.fichanewteam.plantilla.models.Jugador
import org.example.fichanewteam.plantilla.dto.PlantillaDto
import org.example.fichanewteam.plantilla.mapper.toDto
import org.example.fichanewteam.plantilla.mapper.toEntrenador
import org.example.fichanewteam.plantilla.mapper.toJugador
import org.example.fichanewteam.plantilla.models.Entrenador
import org.example.fichanewteam.plantilla.models.Plantilla
import org.lighthousegames.logging.logging
import java.io.File

class PlantillaStorageJson : PlantillaStorage {
    private val logger = logging()

    init {
        logger.debug { "inicializando PersonalStorageJson" }
    }
    // Lee el archivo json y lo transforma a una cadena
    override fun readFile(file: File, format: FileFormat): List<Plantilla> {
        println()
        logger.debug { "Leyendo JSON" }

        if (!file.exists() || !file.isFile || !file.canRead()) {
            throw IllegalArgumentException("El fichero no se puede leer, no es un fichero o no se ha encontrado")
        } else {
            val json = Json { ignoreUnknownKeys = true; prettyPrint = true }
            val imprimirJson = file.readText()
            val listaPlantillaDto = json.decodeFromString<List<PlantillaDto>>(imprimirJson)

            val listaPersonalModel = listaPlantillaDto.map {
                when (it.rol) {
                    "Entrenador" -> it.toEntrenador()
                    else -> it.toJugador()
                }
            }
            return listaPersonalModel
        }
    }

    // Escribe en el archivo json
    override fun writeFile(file: File, format: FileFormat, personal: List<Plantilla>) {
        if (!file.parentFile.exists() || !file.parentFile.isDirectory || !file.canWrite()) {
            throw IllegalArgumentException("El fichero json no se puede sobreescribir o no existe en su directorio padre")
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
    }
}