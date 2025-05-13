package org.example.fichanewteam.plantilla.repositories

import org.example.fichanewteam.plantilla.dao.EntrenadorEntity
import org.example.fichanewteam.plantilla.dao.JugadorEntity
import org.example.fichanewteam.plantilla.dao.PlantillaDao
import org.example.fichanewteam.plantilla.mapper.toEntity
import org.example.fichanewteam.plantilla.mapper.toModel
import org.example.fichanewteam.plantilla.models.Entrenador
import org.example.fichanewteam.plantilla.models.Jugador
import org.example.fichanewteam.plantilla.models.Plantilla

import org.lighthousegames.logging.logging

class PlantillaRepositoryImpl (
    val dao : PlantillaDao
) : PlantillaRepository<Plantilla> {

    private val plantilla = mutableMapOf<Long, Plantilla>()
    private val logger = logging()

    init {
        logger.debug { "Iniciando repositorio" }
    }

    //Función que devuelve una lista de los miembros de la plantilla
    override fun findAll(): List<Plantilla> {
        logger.debug { "Obteniendo toda la plantilla" }
        return dao.findAll().mapNotNull {
            when (it) {
                is EntrenadorEntity -> it.toModel() as? Entrenador
                is JugadorEntity -> it.toModel() as? Jugador
                else -> null // Maneja otros tipos o entidades inesperadas
            }
        }
    }

    //Función que busca a un miembro de la plantilla por id
    override fun findById(id: Long): Plantilla? {
        logger.debug { "Buscando un miembro de la plantilla por id : $id" }
        return dao.findById(id).toModel()
    }

    //Funcion que guarda una entidad
    override fun save(item: Plantilla): Plantilla {
        logger.debug { "Salvando miembro de la plantilla : $item" }
        val save = item.copy(
            id = item.id,
        )
        val dao = dao.save(save.toEntity())

        return item
    }

    //Funcion que actualiza el id de un miembro de la plantilla
    override fun update(id: Long, item: Plantilla): Plantilla? {
        val plantilla = findById(id)
        if (item != null) {
            val updated = item.copy(
                id = item.id,
            )
            dao.save(updated.toEntity())
        }
        return plantilla
    }

    //Función que borra el identificador de un miembro de la plantilla
    override fun delete(id : Long): Plantilla? {
        logger.debug { "Eliminando miembro de la plantilla : $id" }
        val plantilla : Plantilla? = dao.findById(1L).toModel()
        if (plantilla != null) {
            val res = dao.delete(1L)
            if (res == 0L) {
                logger.error { "Fallo al remover el miembro de la plantilla" }
            }
        }
        return plantilla
    }

    //Función que guarda todos los items en una lista
    override fun saveAll(t: List<Plantilla>): List<Plantilla> {
        return t.map { save(it) }
    }

    //Función que elimina toda la informacion sobre un miembro de la plantilla
    override fun deleteAll() {
        logger.debug { "Eliminando datos de un miembro de la plantilla" }
        return dao.deleteAll()
    }

}