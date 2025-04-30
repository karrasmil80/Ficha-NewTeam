
package org.example.fichanewteam.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.models.Personal

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
class Jugador(
    id: Long = 0,
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    fechaIncorporacion: String,
    salario: Double?,
    pais: String,
    rol: String = "Jugador",
    var posicion: String?,
    var dorsal: Int?,
    var altura: Double?,
    var peso: Double?,
    var goles: Int,
    var partidosJugados: Int
): Personal(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol) {

    /**
     * Sobreescribe la cadena que se muestra al imprimir el modelo.
     *
     * @return Una cadena con los detalles del jugador.
     */
    override fun toString(): String {
        return ("Jugador(id=$id, nombre=$nombre, apellidos=$apellidos, fechaNacimiento=$fechaNacimiento, fechaIncorporacion=$fechaIncorporacion, salario=$salario, pais=$pais, posiciom=$posicion, dorsal=$dorsal, altura=$altura, peso=$peso, goles=$goles, partidosJugados=$partidosJugados )")
    }

    /**
     * Crea una copia del objeto jugador con los valores proporcionados.
     *
     * @param id Identificador único del jugador.
     * @param nombre Nombre del jugador.
     * @param apellidos Apellidos del jugador.
     * @param fechaNacimiento Fecha de nacimiento del jugador en formato YYYY-MM-DD.
     * @param fechaIncorporacion Fecha de incorporación del jugador al equipo en formato YYYY-MM-DD.
     * @param salario Salario del jugador.
     * @param pais País de origen del jugador.
     * @param rol Rol del jugador en el equipo (en este caso, siempre será "jugador").
     * @return Una nueva instancia de [Jugador] con los valores proporcionados.
     */
    override fun copy(
        id: Long,
        nombre: String,
        apellidos: String,
        fechaNacimiento: String,
        fechaIncorporacion: String,
        salario: Double,
        pais: String,
        rol: String,
    ): Personal {
        return Jugador(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol, posicion, dorsal, altura, peso, goles, partidosJugados)
    }

    /**
     * Enum que representa las posibles posiciones de un jugador en el campo.
     */
    @Serializable
    enum class Posicion {
        @SerialName("posicion")
        DEFENSA, CENTROCAMPISTA, DELANTERO, PORTERO, NINGUNO
    }
}
