package org.example.fichanewteam.plantilla.dao

import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.kotlin.RegisterKotlinMapper
import org.jdbi.v3.sqlobject.statement.SqlQuery

//PARTE BUENA
@RegisterKotlinMapper(PlantillaEntity::class)
interface PlantillaDao {
    @SqlQuery("SELECT * FROM Plantilla")
    fun findAll(): List<PlantillaEntity>

    @SqlQuery("SELECT * FROM PLANTILLA WHERE id = :id")
    fun findById(@Bind("id") id: Long): PlantillaEntity

    @SqlQuery("INSERT INTO PLANTILLA(id, nombre, apellidos, fecha_nacimiento, fecha_incorporacion, salario, pais, rol)")
    fun save(@BindBean personalentity: PlantillaEntity) : Long

    @SqlQuery("UPDATE PLANTILLA SET id=:id, nombre=:nombre, apellidos=:apellidos, fecha_nacimiento=:fecha_nacimiento, salario=:salario, pais=:pais, rol=:rol")
    fun update(@BindBean personalentity: PlantillaEntity) : Long

    @SqlQuery("DELETE FROM PLANTILLA")
    fun delete(@Bind("id") id: Long) : Long

    /* --> save all
    @SqlQuery
     */

    /* --> delete all
    @SqlQuery
     */


}
//PARTE BUENA