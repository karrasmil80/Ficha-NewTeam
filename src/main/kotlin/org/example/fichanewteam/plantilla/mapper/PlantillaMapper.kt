package org.example.fichanewteam.plantilla.mapper

import org.example.fichanewteam.plantilla.dto.PlantillaDto
import org.example.fichanewteam.plantilla.dao.EntrenadorEntity
import org.example.fichanewteam.plantilla.dao.JugadorEntity
import org.example.fichanewteam.plantilla.dao.PlantillaEntity
import org.example.fichanewteam.plantilla.models.Entrenador
import org.example.fichanewteam.plantilla.models.Jugador
import org.example.fichanewteam.plantilla.models.Plantilla

// Función que convierte PlantillaDto a un modelo (Jugador)
fun PlantillaDto.toJugador(): Jugador {
    return Jugador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fecha_nacimiento,
        fechaIncorporacion = this.fecha_incorporacion,
        salario = this.salario,
        pais = this.pais,
        rol = this.rol,
        posicion = Jugador.Posicion.valueOf(posicion!!).toString(),
        dorsal = this.dorsal ?: 0,
        altura = this.altura ?: 0.0,
        peso = this.peso ?: 0.0,
        goles = this.goles ?: 0,
        partidosJugados = this.partidos_jugados ?: 0,
        minutosJugados = this.minutos_jugados,
        rutaImagen = this.ruta_imagen ?: ""
    )
}

// Función que convierte PlantillaDto a un modelo (Entrenador)
fun PlantillaDto.toEntrenador(): Entrenador {
    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fecha_nacimiento,
        fechaIncorporacion = this.fecha_incorporacion,
        salario = this.salario,
        pais = this.pais,
        rol = this.rol,
        especialidad = Entrenador.Especializacion.valueOf(especialidad!!).toString(),
        rutaImagen = this.ruta_imagen ?: ""
    )
}

// Función de extensión que convierte un PlantillaDto a Plantilla
fun PlantillaDto.toModel(): Plantilla {
    return if (this.rol == "Jugador") {
        Jugador(
            id = id,
            nombre = nombre,
            apellidos = apellidos,
            fechaNacimiento = fecha_nacimiento,
            fechaIncorporacion = fecha_incorporacion,
            salario = salario,
            pais = pais,
            rol = rol,
            posicion = Jugador.Posicion.valueOf(posicion!!).toString(),
            dorsal = dorsal!!,
            altura = altura!!,
            peso = peso!!,
            goles = goles!!,
            partidosJugados = partidos_jugados!!,
            minutosJugados = minutos_jugados,
            rutaImagen = ruta_imagen ?: ""
        )
    } else {
        Entrenador(
            id = id,
            nombre = nombre,
            apellidos = apellidos,
            fechaNacimiento = fecha_nacimiento,
            fechaIncorporacion = fecha_incorporacion,
            salario = salario,
            pais = pais,
            rol = rol,
            especialidad = Entrenador.Especializacion.valueOf(especialidad!!).toString(),
            rutaImagen = ruta_imagen ?: ""
        )
    }
}

// Función que convierte un modelo a una entidad
fun Plantilla.toEntity(): PlantillaEntity {
    return if (rol == "jugador") {
        val jugador = this as Jugador
        JugadorEntity(
            id = jugador.id,
            nombre = jugador.nombre,
            apellidos = jugador.apellidos,
            fechaNacimiento = jugador.fechaNacimiento,
            fechaIncorporacion = jugador.fechaIncorporacion,
            salario = jugador.salario,
            pais = jugador.pais,
            rol = jugador.rol,
            posicion = Jugador.Posicion.valueOf(jugador.posicion!!),
            dorsal = jugador.dorsal!!,
            altura = jugador.altura!!,
            peso = jugador.peso!!,
            goles = jugador.goles!!,
            partidosJugados = jugador.partidosJugados!!,
            minutosJugados = jugador.minutosJugados!!,
            rutaImagen = jugador.rutaImagen!!
        )
    } else {
        val entrenador = this as Entrenador
        EntrenadorEntity(
            id = entrenador.id,
            nombre = entrenador.nombre,
            apellidos = entrenador.apellidos,
            fechaNacimiento = entrenador.fechaNacimiento,
            fechaIncorporacion = entrenador.fechaIncorporacion,
            salario = entrenador.salario,
            pais = entrenador.pais,
            rol = entrenador.rol,
            especialidad = Entrenador.Especializacion.valueOf(entrenador.especialidad),
            rutaImagen = entrenador.rutaImagen
        )
    }
}

// Función que convierte PlantillaEntity a un modelo (Jugador)
fun PlantillaEntity.toJugador(): Jugador {
    val jugador = this as JugadorEntity
    return Jugador(
        id = jugador.id,
        nombre = jugador.nombre,
        apellidos = jugador.apellidos,
        fechaNacimiento = jugador.fechaNacimiento,
        fechaIncorporacion = jugador.fechaIncorporacion,
        salario = jugador.salario,
        pais = jugador.pais,
        rol = jugador.rol,
        posicion = Jugador.Posicion.valueOf(jugador.posicion!!.toString()).toString(),
        dorsal = jugador.dorsal!!,
        altura = jugador.altura!!,
        peso = jugador.peso!!,
        goles = jugador.goles!!,
        partidosJugados = jugador.partidosJugados!!,
        minutosJugados = jugador.minutosJugados,
        rutaImagen = jugador.rutaImagen
    )
}

// Función que convierte PlantillaEntity a un modelo (Entrenador)
fun PlantillaEntity.toEntrenador(): Entrenador {
    val entrenador = this as EntrenadorEntity
    return Entrenador(
        id = entrenador.id,
        nombre = entrenador.nombre,
        apellidos = entrenador.apellidos,
        fechaNacimiento = entrenador.fechaNacimiento,
        fechaIncorporacion = entrenador.fechaIncorporacion,
        salario = entrenador.salario,
        pais = entrenador.pais,
        rol = entrenador.rol,
        especialidad = Entrenador.Especializacion.valueOf(entrenador.especialidad!!.toString()).toString(),
        rutaImagen = entrenador.rutaImagen
    )
}

// Función que convierte PlantillaEntity a modelo diferenciando entre jugador y entrenador
fun PlantillaEntity.toModel(): Plantilla {
    return if (rol == "jugador") {
        this.toJugador()
    } else {
        this.toEntrenador()
    }
}

// --- FUNCIONES DE LISTAS ---


@JvmName("modelToDtoList")
fun List<PlantillaDto>.toModel(): List<Plantilla> {
    return map { it.toModel() }
}

@JvmName("dtoToModelList")
fun List<Plantilla>.toDto(): List<PlantillaDto> {
    return map { it.toDto() }
}

@JvmName("entityToModelList")
fun List<PlantillaEntity>.toModel(): List<Plantilla> {
    return map { it.toModel() }
}

// Extensión para modelo a DTO (faltaba esta parte en tu código)
fun Plantilla.toDto(): PlantillaDto {
    return if (rol == "jugador") {
        val jugador = this as Jugador
        PlantillaDto(
            id = jugador.id,
            nombre = jugador.nombre,
            apellidos = jugador.apellidos,
            fecha_nacimiento = jugador.fechaNacimiento,
            fecha_incorporacion = jugador.fechaIncorporacion,
            salario = jugador.salario!!,
            pais = jugador.pais,
            rol = jugador.rol,
            posicion = jugador.posicion,
            dorsal = jugador.dorsal,
            altura = jugador.altura,
            peso = jugador.peso,
            goles = jugador.goles,
            partidos_jugados = jugador.partidosJugados,
            minutos_jugados = jugador.minutosJugados,
            ruta_imagen = jugador.rutaImagen,
            especialidad = null
        )
    } else {
        val entrenador = this as Entrenador
        PlantillaDto(
            id = entrenador.id,
            nombre = entrenador.nombre,
            apellidos = entrenador.apellidos,
            fecha_nacimiento = entrenador.fechaNacimiento,
            fecha_incorporacion = entrenador.fechaIncorporacion,
            salario = entrenador.salario!!,
            pais = entrenador.pais,
            rol = entrenador.rol,
            especialidad = entrenador.especialidad,
            ruta_imagen = entrenador.rutaImagen!!,
            posicion = null,
            dorsal = null,
            altura = null,
            peso = null,
            goles = null,
            partidos_jugados = null,
            minutos_jugados = null
        )
    }
}
