package org.example.fichanewteam.plantilla.mapper

import org.example.fichanewteam.models.Jugador
import org.example.fichanewteam.plantilla.dto.JugadorDto
import org.example.fichanewteam.plantilla.dto.PersonalDto
import org.example.fichanewteam.plantilla.dao.JugadorEntity
import org.example.models.Plantilla

fun Jugador.toDto(): PersonalDto {
    return PersonalDto(
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
    )
}

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
        rol = this.rol
    )
}

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
        rol = this.rol
    )
}

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
        rol = this.rol
    )
}

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
        rol = this.rol
    )
}
