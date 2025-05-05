
package org.example.fichanewteam.plantilla.dto

import kotlinx.serialization.Serializable
import org.example.fichanewteam.plantilla.models.Jugador

/**
 * Clase que representa la transferencia de datos (DTO) de un jugador.
 * Esta clase se utiliza para enviar y recibir datos de un jugador entre diferentes capas de la aplicación o a través de la red.
 *
 * @param id Identificador único del jugador.
 * @param nombre Nombre del jugador.
 * @param apellidos Apellidos del jugador.
 * @param fechaNacimiento Fecha de nacimiento del jugador en formato YYYY-MM-DD.
 * @param fechaIncorporacion Fecha de incorporación del jugador al equipo en formato YYYY-MM-DD.
 * @param salario Salario del jugador.
 * @param pais País de origen del jugador.
 * @param rol Rol del jugador en el equipo (en este caso, siempre será "jugador").
 * @param posicion Posición del jugador en el campo (defensa, centrocampista, delantero, portero).
 * @param dorsal Número de camiseta del jugador.
 * @param altura Altura del jugador.
 * @param peso Peso del jugador.
 * @param goles Número de goles anotados por el jugador.
 * @param partidosJugados Número de partidos jugados por el jugador.
 */

//PARTE BUENA
@Serializable
class JugadorDto(
    val id: Long,
    val nombre: String,
    val apellidos: String,
    val fechaNacimiento: String,
    val fechaIncorporacion: String,
    val salario: Double,
    val pais: String,
    val rol: String,
    val posicion: Jugador.Posicion,
    val dorsal: Int,
    val altura: Double,
    val peso: Double,
    val goles: Int,
    val partidosJugados: Int
) : java.io.Serializable
//PARTE BUENA