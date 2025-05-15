package org.example.fichanewteam.plantilla.storage
/*
import com.github.michaelbull.result.Result
import org.example.fichanewteam.plantilla.models.Jugador
import org.example.fichanewteam.plantilla.dto.PlantillaDto
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.mapper.toModel
import org.example.fichanewteam.plantilla.models.Entrenador
import org.example.fichanewteam.plantilla.models.Plantilla
import org.lighthousegames.logging.logging
import java.io.File

class PlantillaStorageCsv : PlantillaStorage {
    //Implementación del logger
    private val logger = logging()
    init {
        logger.debug { "Iniciando almacenamiento en CSV" }
    }

    //Lee el fichero y lo transforma a una lista del tipo PersonalDto
    override fun readFile(file: File, format: FileFormat): List<Plantilla> {
        logger.debug { "Leyendo fichero CSV" }

        //Filtra errores de lectura del archivo
        if (!file.exists() || !file.isFile || !file.canRead() || !file.canRead() || file.length() == 0L || !file.name.endsWith(".csv")) {
            logger.error { "El fichero no existe o no se puede leer: $file" }
            throw IllegalArgumentException("El fichero no existe o no se puede leer")
        }

        //Crea una lista del tipo Personal Dto con todos sus datos, saltandose la cabecera, separando por la comas,
        //elimina los espacios en blanco del string y por ultimo la convierte a model.
        val lista = file.readLines()
            .drop(1)
            .map { it.split(",") }
            .map { it.map { it.trim() } }
            .map{
                PlantillaDto(
                    id = it[0].toLong(),
                    nombre = it[1],
                    apellidos = it[2],
                    fecha_nacimiento = it[3],
                    fecha_incorporacion = it[4],
                    salario = it[5].toDouble(),
                    pais = it[6],
                    rol = it[7],
                    especialidad = it[8],
                    posicion = it[9],
                    dorsal = it[10].toIntOrNull(),
                    altura = it[11].toDoubleOrNull(),
                    peso = it[12].toDoubleOrNull(),
                    goles = it[13].toIntOrNull(),
                    partidos_jugados = it[14].toIntOrNull(),
                    minutos_jugados = it[15].toDoubleOrNull(),
                    ruta_imagen = it[16]


                ).toModel()
            }
        return lista
    }

    //Sobreescribe la lista de personal añadiendo entradas.
    override fun writeFile(file: File, format: FileFormat, personal: List<Plantilla>) {

        //Logger
        logger.debug { "Escribiendo fichero CSV" }

        //Filtra errores de lectura del archivo
        if (!file.parentFile.exists() || !file.parentFile.isDirectory || !file.name.endsWith(".csv")) {
            logger.error { "El directorio padre del fichero no se encuentra o no existe" }
            throw IllegalArgumentException("El directorio padre no existe")
        }

        //Sobreescribe con los datos proporcionados la lista del tipo personal dependiendo de si es jugador o entrenador
        personal.forEach {
            when (it) {
                is Jugador -> file.appendText("${it.id}, ${it.nombre}, ${it.apellidos}, ${it.fechaNacimiento}, ${it.fechaIncorporacion}, ${it.salario}, ${it.pais}, ${it.rol}, ${it.posicion}, ${it.dorsal}, ${it.altura}, ${it.peso}, ${it.goles}, ${it.partidosJugados} /n")
                is Entrenador -> file.appendText("${it.id}, ${it.nombre}, ${it.apellidos}, ${it.fechaNacimiento}, ${it.fechaIncorporacion}, ${it.salario}, ${it.pais}, ${it.rol}, ${it.especialidad} /n")
            }
        }
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
 */


