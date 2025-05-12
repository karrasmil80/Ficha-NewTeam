package org.example.fichanewteam.plantilla.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//PARTE BUENA
/**
 * Clase abstracta que representa a una persona del equipo.
 * Esta clase se utiliza como base para definir jugadores y entrenadores.
 *
 * @param id Identificador único de la persona.
 * @param nombre Nombre de la persona.
 * @param apellidos Apellidos de la persona.
 * @param fechaNacimiento Fecha de nacimiento de la persona en formato YYYY-MM-DD.
 * @param fechaIncorporacion Fecha de incorporación de la persona al equipo en formato YYYY-MM-DD.
 * @param salario Salario de la persona.
 * @param pais País de origen de la persona.
 * @param rol Rol de la persona en el equipo (jugador o entrenador).
 */
@Serializable
abstract class Plantilla(
    val id: Long,
    var nombre: String,
    var apellidos: String,
    var fechaNacimiento: String,
    var fechaIncorporacion: String,
    var salario: Double?,
    var pais: String,
    val rol: String,
    var rutaImagen: String = ""
) {

    companion object{
        val NEW_ID = 1L
    }

    /**
     * Crea una copia del objeto [Plantilla] con los valores proporcionados.
     *
     * @param id Identificador único de la persona.
     * @param nombre Nombre de la persona.
     * @param apellidos Apellidos de la persona.
     * @param fechaNacimiento Fecha de nacimiento de la persona en formato YYYY-MM-DD.
     * @param fechaIncorporacion Fecha de incorporación de la persona al equipo en formato YYYY-MM-DD.
     * @param salario Salario de la persona.
     * @param pais País de origen de la persona.
     * @param rol Rol de la persona en el equipo (jugador o entrenador).
     * @return Una nueva instancia de [Plantilla] con los valores proporcionados.
     */

    abstract fun copy(
        id: Long = this.id,
        nombre: String = this.nombre,
        apellidos: String = this.apellidos,
        fechaNacimiento: String = this.fechaNacimiento,
        fechaIncorporacion: String = this.fechaNacimiento,
        salario: Double = this.salario!!,
        pais: String = this.pais,
        rol: String = this.rol,
        rutaImagen: String = this.rutaImagen
    ): Plantilla

    /**
     * Enum que representa los posibles tipos de roles en el equipo.
     */
    @Serializable
    enum class Tipo {
        @SerialName("tipo")
        Jugador, Entrenador,
        @SerialName("")
        NINGUNO
    }
}
//PARTE BUENA