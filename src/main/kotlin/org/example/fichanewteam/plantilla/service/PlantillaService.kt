package org.example.fichanewteam.plantilla.service

import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.models.Plantilla
import com.github.michaelbull.result.Result
import org.example.fichanewteam.plantilla.models.Jugador
import org.example.fichanewteam.plantilla.storage.FileFormat
import java.io.File

interface PlantillaService {

    /**
     * Obtiene una lista de todas las entidades.
     *
     * @return una lista de todas las entidades de tipo [T].
     */
    fun findAll(): Result<List<Jugador>, PlantillaError>

    /**
     * Busca una entidad por su identificador único.
     *
     * @param id el identificador único de la entidad.
     * @return la entidad correspondiente al identificador, o null si no se encuentra.
     */
    fun findById(id: Long): Result<Plantilla, PlantillaError>

    /**
     * Guarda una nueva entidad en el repositorio.
     *
     * @param item la entidad a guardar.
     * @return la entidad guardada con éxito.
     */
    fun save(item : Plantilla): Result<Plantilla, PlantillaError>

    /**
     * Actualiza una entidad existente en el repositorio.
     *
     * @param id el identificador único de la entidad a actualizar.
     * @param item la entidad con los nuevos valores.
     * @return la entidad actualizada, o null si no se encuentra la entidad original.
     */
    fun update(id: Long, item: Plantilla): Result<Plantilla, PlantillaError>

    /**
     * Elimina una entidad su identificador único.
     *
     * @param id el identificador único de la entidad a eliminar.
     * @return la entidad eliminada, o null si no se encuentra la entidad.
     */
    fun deleteById(id: Long): Result<Unit, PlantillaError>

    /**
     * Lee un archivo con información de la plantilla y lo convierte en una lista de objetos [Plantilla].
     *
     * @param file Archivo de entrada que contiene los datos.
     * @param format Formato del archivo (JSON, CSV, XML).
     * @return Una lista de objetos de plantilla
     */
    fun readFile(file: File, format: FileFormat): List<Plantilla>

    /**
     * Escribe una lista de objetos de plantilla en un archivo en el formato especificado.
     *
     * @param file Archivo de salida donde se escribirán los datos.
     * @param format Formato en el que se guardarán los datos (JSON, CSV, XML).
     * @param personal Lista de objetos de plantilla a guardar en el archivo.
     */
    fun writeFile(file: File, format: FileFormat, personal: List<Plantilla>)

    /**
     * Funcion que elimina toda informacion sobre una entidad
     *
     */
    fun deleteAll(): Result<Unit, PlantillaError>

    /**
     * Función para guardar multiples objetos al mismo tiempo
     *
     * @param item entidad a guardar
     * @return la lista de las entidades
     */
    fun saveAll(plantilla: List<Plantilla>): Result<List<Plantilla>, PlantillaError>
}