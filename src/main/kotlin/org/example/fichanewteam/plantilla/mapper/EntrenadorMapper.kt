package org.example.fichanewteam.plantilla.mapper

import org.example.fichanewteam.plantilla.dto.EntrenadorDto
import org.example.fichanewteam.plantilla.dto.PlantillaDto
import org.example.fichanewteam.plantilla.dao.EntrenadorEntity
import org.example.fichanewteam.plantilla.models.Entrenador

//PARTE BUENA
fun Entrenador.toDto(): PlantillaDto {
    return PlantillaDto(
        id = id,
        nombre = nombre,
        apellidos = apellidos,
        fecha_nacimiento = fechaNacimiento.toString(),
        fecha_incorporacion = fechaIncorporacion.toString(),
        salario = salario!!,
        pais = pais,
        especialidad = especialidad.toString(),
        rol = "Entrenador",
        posicion = "", // No aplica para entrenadores
        dorsal = null, // No aplica para entrenadores
        altura = null, // No aplica para entrenadores
        peso = null, // No aplica para entrenadores
        goles = null, // No aplica para entrenadores
        partidos_jugados = null,
        ruta_imagen = this.rutaImagen // No aplica para entrenadores
    )
}

fun EntrenadorDto.toModel(): Entrenador {
    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        especialidad = especialidad.toString(),
        rol = this.rol,
        rutaImagen = this.rutaImagen
    )
}

fun Entrenador.toEntity(): EntrenadorEntity {
    return EntrenadorEntity(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        especialidad = Entrenador.Especializacion.valueOf(this.especialidad),
        rol = this.rol,
        rutaImagen = this.rutaImagen
    )
}

fun EntrenadorEntity.toModel(): Entrenador {
    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        especialidad = this.especialidad.toString(),
        rol = this.rol,
        rutaImagen = this.rutaImagen
    )
}

fun EntrenadorDto.toEntity(): EntrenadorEntity {
    return EntrenadorEntity(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        fechaIncorporacion = this.fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        especialidad = this.especialidad,
        rol = this.rol,
        rutaImagen = this.rutaImagen
    )
}
//PARTE BUENA