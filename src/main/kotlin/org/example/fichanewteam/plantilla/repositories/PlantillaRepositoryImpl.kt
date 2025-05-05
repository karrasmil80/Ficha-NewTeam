package org.example.fichanewteam.plantilla.repositories

import org.example.fichanewteam.plantilla.dao.PlantillaDao
import org.example.fichanewteam.plantilla.mapper.toEntity
import org.example.fichanewteam.plantilla.mapper.toModel
import org.example.fichanewteam.plantilla.models.Plantilla
import org.lighthousegames.logging.logging

//PARTE BUENA
class PlantillaRepositoryImpl (
    val dao : PlantillaDao
) : PlantillaRepository<Plantilla> {

    private val plantilla = mutableMapOf<Long, Plantilla>()
    private val logger = logging()

    init {
        logger.debug { "Iniciando repositorio" }
    }
    override fun findAll(): List<Plantilla> {
        logger.debug { "Obteniendo toda la plantilla" }
        return dao.findAll().map { it.toModel() }
    }

    override fun findById(id: Long): Plantilla? {
        logger.debug { "Buscando un miembro de la plantilla por id : $id" }
        return dao.findById(id).toModel()
    }

    override fun save(item: Plantilla): Plantilla {
        logger.debug { "Salvando miembro de la plantilla : $item" }
        val save = item.copy(
            id = item.id,
        )
        val dao = dao.save(save.toEntity())

        return item
    }

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

    // --> save all

    // --> delete all / logical
}
//PARTE BUENA