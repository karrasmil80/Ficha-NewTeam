import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Clase que representa la transferencia de datos (DTO) de una persona del equipo.
 * Esta clase se utiliza para enviar y recibir datos de una persona.
 *
 * @param id Identificador único de la persona.
 * @param nombre Nombre de la persona.
 * @param apellidos Apellidos de la persona.
 * @param fecha_nacimiento Fecha de nacimiento de la persona en formato YYYY-MM-DD.
 * @param fecha_incorporacion Fecha de incorporación de la persona al equipo en formato YYYY-MM-DD.
 * @param salario Salario de la persona.
 * @param pais País de origen de la persona.
 * @param rol Rol de la persona en el equipo (jugador o entrenador).
 * @param especialidad Especialidad del entrenador (si se da, puede ser null).
 * @param posicion Posición del jugador en el campo (si se da, puede ser null).
 * @param dorsal Número de camiseta del jugador (si se da, puede ser null).
 * @param altura Altura del jugador (si se da, puede ser null).
 * @param peso Peso del jugador (si se da, puede ser null).
 * @param goles Número de goles anotados por el jugador (si se da, puede ser null).
 * @param partidos_jugados Número de partidos jugados por el jugador (si se da, puede ser null).
 */

@Serializable
data class  PersonalDto (
    @SerialName("id")
    var id: Long,
    @SerialName("nombre")
    var nombre: String,
    @SerialName("apellidos")
    var apellidos: String,
    @SerialName("fecha_nacimiento")
    var fecha_nacimiento: String,
    @SerialName("fecha_incorporacion")
    var fecha_incorporacion: String,
    @SerialName("salario")
    var salario: Double,
    @SerialName("pais")
    var pais: String,
    @SerialName("rol")
    val rol: String,
    @SerialName("especialidad")
    val especialidad: String?,
    @SerialName("posicion")
    var posicion: String?,
    @SerialName("dorsal")
    var dorsal: Int?,
    @SerialName("altura")
    var altura: Double?,
    @SerialName("peso")
    var peso: Double?,
    @SerialName("goles")
    var goles: Int?,
    @SerialName("partidos_jugados")
    var partidos_jugados: Int?
) : java.io.Serializable