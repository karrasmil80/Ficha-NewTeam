package org.example.fichanewteam.plantilla.dao

/**
 * @param id Identificador único de la persona.
 * @param nombre Nombre de la persona.
 * @param apellidos Apellidos de la persona.
 * @param fechaNacimiento Fecha de nacimiento de la persona en formato YYYY-MM-DD.
 * @param fechaIncorporacion Fecha de incorporación de la persona al equipo en formato YYYY-MM-DD.
 * @param salario Salario de la persona.
 * @param pais País de origen de la persona.
 * @param rol Rol de la persona en el equipo (jugador o entrenador).
 */

//PARTE BUENA
open class PlantillaEntity(
    val id: Long,
    var nombre: String,
    var apellidos: String,
    var fechaNacimiento: String,
    var fechaIncorporacion: String,
    var salario: Double?,
    var pais: String,
    val rol: String,
    var rutaImagen: String
)
//PARTE BUENA