
package org.example.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
class Entrenador(
    id: Long = 0,
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    fechaIncorporacion: String,
    salario: Double?,
    pais: String,
    rol: String = "Entrenador",
    var especialidad: String

) : Plantilla(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol){ //tipo){

    /**
     * Sobreescribe la cadena que se muestra al imprimir el modelo.
     *
     * @return Una cadena con los detalles del entrenador.
     */
    override fun toString(): String{
        return("Entrenador(id=$id, nombre=$nombre, apellidos=$apellidos, fechaNacimiento=$fechaNacimiento, fechaIncorporacion=$fechaIncorporacion, salario=$salario, pais=$pais, especializacion=$especialidad)")
    }

    /**
     * Crea una copia del objeto entrenador con los valores proporcionados.
     *
     * @param id Identificador único del entrenador.
     * @param nombre Nombre del entrenador.
     * @param apellidos Apellidos del entrenador.
     * @param fechaNacimiento Fecha de nacimiento del entrenador en formato YYYY-MM-DD.
     * @param fechaIncorporacion Fecha de incorporación del entrenador al equipo en formato YYYY-MM-DD.
     * @param salario Salario del entrenador.
     * @param pais País de origen del entrenador.
     * @param rol Rol del entrenador en el equipo (en este caso, siempre será "entrenador").
     * @return Una nueva instancia de [Entrenador] con los valores proporcionados.
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
    ): Plantilla {
        return Entrenador(id, nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol, especialidad)
    }

    /**
     * Enum que representa las especializaciones posibles de un entrenador.
     */
    @Serializable
    enum class Especializacion {
        @SerialName("especialidad")
        ENTRENADOR_ASISTENTE, ENTRENADOR_PORTEROS, ENTRENADOR_PRINCIPAL
    }
}
