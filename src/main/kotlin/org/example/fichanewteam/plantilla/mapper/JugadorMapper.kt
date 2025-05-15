package org.example.fichanewteam.plantilla.mapper

import org.example.fichanewteam.plantilla.models.Jugador
import org.example.fichanewteam.plantilla.dto.JugadorDto
import org.example.fichanewteam.plantilla.dto.PlantillaDto
import org.example.fichanewteam.plantilla.dao.JugadorEntity
import org.example.fichanewteam.plantilla.models.Plantilla
import org.example.fichanewteam.plantilla.viewmodel.PlantillaViewModel
import org.example.fichanewteam.plantilla.viewmodel.PlantillaViewModel.JugadorState
import kotlin.toString

//Función que convierte el modelo a dto
fun Jugador.toDto(): PlantillaDto {
    return PlantillaDto(
        id = id,
        nombre = nombre,
        apellidos = apellidos,
        fecha_nacimiento = fechaNacimiento,
        fecha_incorporacion = fechaIncorporacion,
        salario = salario!!,
        pais = pais,
        posicion = posicion.toString(),
        dorsal = dorsal,
        altura = altura,
        peso = peso,
        goles = goles,
        partidos_jugados = partidosJugados,
        rol = rol,
        especialidad = "", // No se aplica a jugadores
        ruta_imagen = rutaImagen,
        minutos_jugados = minutosJugados
    )
}

//Función que convierte un dto a modelo
fun JugadorDto.toModel(): Plantilla {
    return Jugador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        posicion = this.posicion.toString(),
        dorsal = this.dorsal,
        altura = this.altura,
        peso = this.peso,
        goles = this.goles,
        partidosJugados = this.partidosJugados,
        rol = this.rol,
        minutosJugados = this.minutosJugados,
        rutaImagen = this.rutaImagen,
    )
}

//Función que convierte un modelo a entidad (para el dao)
fun Jugador.toEntity(): JugadorEntity {
    return JugadorEntity(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaNacimiento,
        salario = this.salario,
        pais = this.pais,
        posicion = Jugador.Posicion.valueOf(this.posicion.toString()),
        dorsal = this.dorsal,
        altura = this.altura,
        peso = this.peso,
        goles = this.goles,
        partidosJugados = this.partidosJugados,
        rol = this.rol,
        rutaImagen = this.rutaImagen,
        minutosJugados = this.minutosJugados!!
    )
}

//Función que convierte una entidad a modelo
fun JugadorEntity.toModel(): Jugador {
    return Jugador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaNacimiento,
        salario = this.salario,
        pais = this.pais,
        posicion = Jugador.Posicion.valueOf(this.posicion.toString()).toString(),
        dorsal = this.dorsal,
        altura = this.altura,
        peso = this.peso,
        goles = this.goles,
        partidosJugados = this.partidosJugados,
        rol = this.rol,
        rutaImagen = this.rutaImagen,
        minutosJugados = this.minutosJugados
    )
}

//Función que convierte un dto a entidad
fun JugadorDto.toEntity(): JugadorEntity {
    return JugadorEntity(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaNacimiento,
        salario = this.salario,
        pais = this.pais,
        posicion = this.posicion,
        dorsal = this.dorsal,
        altura = this.altura,
        peso = this.peso,
        goles = this.goles,
        partidosJugados = this.partidosJugados,
        rol = this.rol,
        rutaImagen = this.rutaImagen,
        minutosJugados = this.minutosJugados
    )
}

    fun JugadorState.toModel(): Jugador {
        return Jugador(
            id = this.id,
            nombre = this.nombre,
            apellidos = this.apellidos,
            fechaNacimiento = this.fechaNacimiento,
            fechaIncorporacion = this.fechaNacimiento,
            salario = this.salario,
            pais = this.pais,
            posicion = Jugador.Posicion.valueOf(this.posicion.toString()).toString(),
            dorsal = this.dorsal,
            altura = this.altura,
            peso = this.peso,
            goles = this.goles,
            partidosJugados = this.partidosJugados,
            rol = this.rol,
            minutosJugados = this.minutosJugados
        )
    }
