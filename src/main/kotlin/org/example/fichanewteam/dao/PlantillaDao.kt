package org.example.fichanewteam.dao

import org.example.models.Personal
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.kotlin.RegisterKotlinMapper
import org.jdbi.v3.sqlobject.statement.SqlQuery

@RegisterKotlinMapper(PersonalEntity::class)
interface PlantillaDao {
    @SqlQuery("SELECT * FROM Plantilla")
    fun findAll(): List<PersonalEntity>

    @SqlQuery("SELECT * FROM Plantila WHERE id = :id")
    fun findById(@Bind("id") id: Long): PersonalEntity

    @SqlQuery("INSERT INTO Plantila(id, nombre, apellidos, fecha_nacimiento, fecha_incorporacion, salario, pais, rol)")
    fun save(@BindBean personalentity: PersonalEntity) : Long

    @SqlQuery("UPDATE Plantilla SET id=:id, nombre=:nombre, apellidos=:apellidos, fecha_nacimiento=:fecha_nacimiento, salario=:salario, pais=:pais, rol=:rol")
    fun update(@BindBean personalentity: PersonalEntity) : Long

    @SqlQuery("DELETE FROM Plantila")
    fun delete(@Bind("id") id: Long) : Long

    /* --> save all
    @SqlQuery
     */

    /* --> delete all
    @SqlQuery
     */


}