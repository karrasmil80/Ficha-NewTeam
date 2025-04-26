package org.example.fichanewteam.plantilla.repositories

import org.example.fichanewteam.plantilla.dao.PlantillaDao
import org.example.fichanewteam.plantilla.mapper.toEntity
import org.example.fichanewteam.plantilla.mapper.toModel
import org.example.models.Personal
import org.lighthousegames.logging.logging

class PlantillaRepositoryImpl (
    val dao : PlantillaDao
) : PlantillaRepository<Personal> {

    private val personal = mutableMapOf<Long, Personal>()
    private val logger = logging()

    init {
        logger.debug { "Iniciando repositorio" }
    }
    override fun findAll(): List<Personal> {
        logger.debug { "Obteniendo toda la plantilla" }
        return dao.findAll().map { it.toModel() }
    }

    override fun findById(id: Long): Personal? {
        logger.debug { "Buscando un miembro de la plantilla por id : $id" }
        return dao.findById(id).toModel()
    }

    override fun save(item: Personal): Personal {
        logger.debug { "Salvando miembro de la plantilla : $item" }
        val save = item.copy(
            id = item.id,
        )
        val dao = dao.save(save.toEntity())

        return item
    }

    override fun update(id: Long, item: Personal): Personal? {
        val plantilla = findById(id)
        if (item != null) {
            val updated = item.copy(
                id = item.id,
            )
            dao.save(updated.toEntity())
        }
        return plantilla
    }

    override fun delete(item: Personal): Personal? {
        logger.debug { "Eliminando miembro de la plantilla : $item" }
        val plantilla = dao.findById(1L)
        if (plantilla != null) {
            val res = dao.delete(1L)
            if (res == 0L) {
                logger.error { "Fallo al remover el miembro de la plantilla" }
            }
        }
        return item
    }

    // --> save all

    // --> delete all / logical
}