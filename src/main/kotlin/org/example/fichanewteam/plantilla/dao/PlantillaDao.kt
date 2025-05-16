package org.example.fichanewteam.plantilla.dao

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.kotlin.RegisterKotlinMapper
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.lighthousegames.logging.logging

@RegisterKotlinMapper(PlantillaEntity::class)
interface PlantillaDao {

    //Consulta que selecciona a todos los miembros de la plantilla
    @SqlQuery("SELECT * FROM plantilla")
    fun findAll(): List<PlantillaEntity>

    //Consulta que selecciona miembros de la plantilla por su id
    @SqlQuery("SELECT * FROM PLANTILLA WHERE id = :id")
    fun findById(@Bind("id") id: Long): PlantillaEntity

    //Consulta que añade miembros a la tabla plantilla
    @SqlUpdate("INSERT INTO plantilla (id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol) VALUES (:id, :nombre, :apellidos, :fechaNacimiento, :fechaIncorporacion, :salario, :paais, :rol)")
    fun save(@BindBean personalentity: PlantillaEntity) : Long

    /*
    //Consulta que actualiza el estado de un miembro de la plantilla
    @SqlUpdate("UPDATE plantilla SET id=:id, nombre=:nombre, apellidos=:apellidos, fechaNacimiento=:fecha_nacimiento, salario=:salario, pais=:pais, rol=:rol")
    fun update(@BindBean personalentity: PlantillaEntity) : Long

     */

    //Cosulta que elimina a un miembro de la plantilla por id
    @SqlUpdate("DELETE FROM plantilla")
    fun delete(@Bind("id") id: Long) : Long

    //Consulta que elimina toda la informacion de miembros de la plantilla por id
    @SqlUpdate("DELETE FROM plantilla")
    fun deleteAll()

}

    fun providePlantillaDao(jdbi: Jdbi): PlantillaDao {
        val logger = logging()
        logger.debug { "Inicializando AlumnosDao" }
        return jdbi.onDemand(PlantillaDao::class.java)
    }
