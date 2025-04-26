
package org.example.Dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.models.Entrenador

/**
 * Clase que representa la transferencia de datos (DTO) de un entrenador.
 * Esta clase se utiliza para enviar y recibir datos de un entrenador entre diferentes capas de la aplicación o a través de la red.
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
@Serializable
class EntrenadorDto(
    val id: Long,
    val nombre: String,
    val apellidos: String,
    val fechaNacimiento: String,
    val fechaIncorporacion: String,
    val salario: Double,
    val pais: String,
    val rol: String,
    @SerialName("especialidad")
    val especialidad: Entrenador.Especializacion,
) : java.io.Serializable
