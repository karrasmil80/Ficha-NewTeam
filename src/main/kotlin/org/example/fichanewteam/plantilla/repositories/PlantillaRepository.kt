package org.example.fichanewteam.plantilla.repositories

import org.example.fichanewteam.plantilla.models.Plantilla


interface PlantillaRepository {
    fun findAll(): List<Plantilla>
    fun findById(id: Long): Plantilla?
    fun save(alumno: Plantilla): Plantilla
    fun deleteById(id: Long)
    fun deleteAll()
    fun saveAll(alumnos: List<Plantilla>): List<Plantilla>
}
