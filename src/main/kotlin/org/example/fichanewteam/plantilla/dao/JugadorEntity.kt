package org.example.fichanewteam.plantilla.dao

import org.example.fichanewteam.plantilla.models.Jugador.Posicion

/**
 * Clase que representa a un jugador del equipo.
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
 * @param altura Altura del jugador en metros.
 * @param peso Peso del jugador en kilogramos.
 * @param goles Número de goles anotados por el jugador.
 * @param partidosJugados Número de partidos jugados por el jugador.
 */
//PARTE BUENA
class JugadorEntity(
    id: Long,
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    fechaIncorporacion: String,
    salario: Double?,
    pais: String,
    rol: String,
    var posicion: Posicion?,
    var dorsal: Int?,
    var altura: Double?,
    var peso: Double?,
    var goles: Int,
    var partidosJugados: Int,
    rutaImagen: String
) : PlantillaEntity(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol, rutaImagen)
//PARTE BUENA