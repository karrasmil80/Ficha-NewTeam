package org.example.fichanewteam.plantilla.dao

import org.example.models.Entrenador.Especializacion

/**
 * Clase que representa a un entrenador del equipo.
 *
 * @param id Identificador único del entrenador.
 * @param nombre Nombre del entrenador.
 * @param apellidos Apellidos del entrenador.
 * @param fechaNacimiento Fecha de nacimiento del entrenador en formato YYYY-MM-DD.
 * @param fechaIncorporacion Fecha de incorporación del entrenador al equipo en formato YYYY-MM-DD.
 * @param salario Salario del entrenador.
 * @param pais País de origen del entrenador.
 * @param rol Rol del entrenador en el equipo (en este caso, siempre será "entrenador").
 * @param especialidad Especialización del entrenador (principal, asistente, o entrenador de porteros).
 */

class EntrenadorEntity(
    id: Long,
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    fechaIncorporacion: String,
    salario: Double?,
    pais: String,
    rol: String,
    var especialidad: Especializacion?
) : PersonalEntity(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol)