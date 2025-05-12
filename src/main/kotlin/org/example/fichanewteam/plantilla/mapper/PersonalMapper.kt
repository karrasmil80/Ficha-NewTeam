package org.example.fichanewteam.plantilla.mapper

import org.example.fichanewteam.plantilla.dto.PersonalDto
import org.example.fichanewteam.plantilla.dao.EntrenadorEntity
import org.example.fichanewteam.plantilla.dao.JugadorEntity
import org.example.fichanewteam.plantilla.dao.PersonalEntity
import org.example.fichanewteam.plantilla.models.Entrenador
import org.example.fichanewteam.plantilla.models.Jugador
import org.example.fichanewteam.plantilla.models.Plantilla

//PARTE BUENA
fun PersonalDto.toJugador(): Jugador {
    return Jugador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fecha_nacimiento,
        fechaIncorporacion = this.fecha_incorporacion,
        salario = this.salario,
        pais = this.pais,
        rol = this.rol,
        posicion = Jugador.Posicion.valueOf(posicion!!).toString(), // Convierte la posición de texto a enum
        dorsal = this.dorsal ?: 0, // Si es nulo, asigna 0
        altura = this.altura ?: 0.0, // Si es nulo, asigna 0.0
        peso = this.peso ?: 0.0, // Si es nulo, asigna 0.0
        goles = this.goles ?: 0, // Si es nulo, asigna 0
        partidosJugados = this.partidos_jugados ?: 0 // Si es nulo, asigna 0
    )
}

//Funcion de extension para convertir un PersonalDto a Entrenador
fun PersonalDto.toEntrenador(): Entrenador {
    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fecha_nacimiento,
        fechaIncorporacion = this.fecha_incorporacion,
        salario = this.salario,
        pais = this.pais,
        rol = this.rol,
        especialidad = Entrenador.Especializacion.valueOf(especialidad!!).toString(), // Convierte la especialidad a enum
        rutaImagen = this.ruta_imagen ?: ""
    )
}

//Funcion de extension que convierte un PersonalDto a Personla, eligiendo despues entre Jugaodr y Entrenador
fun PersonalDto.toModel(): Plantilla {
    return if (this.rol == "Jugador") {
        Jugador(
            id = id,
            nombre = nombre,
            apellidos = apellidos,
            fechaNacimiento = fecha_nacimiento,
            fechaIncorporacion = fecha_incorporacion,
            salario = salario,
            pais = pais,
            posicion = Jugador.Posicion.valueOf(posicion!!).toString(), // Convierte la posición a enum
            dorsal = dorsal!!, // Asume que no es nulo para jugadores
            altura = altura!!, // Asume que no es nulo para jugadores
            peso = peso!!, // Asume que no es nulo para jugadores
            goles = goles!!, // Asume que no es nulo para jugadores
            partidosJugados = this.partidos_jugados!!, // Asume que no es nulo para jugadores
            rol = this.rol,
            rutaImagen = this.ruta_imagen ?: ""
        )
    } else {
        Entrenador(
            id = id,
            nombre = nombre,
            apellidos = apellidos,
            fechaNacimiento = this.fecha_nacimiento,
            fechaIncorporacion = this.fecha_incorporacion,
            salario = salario,
            pais = pais,
            especialidad = Entrenador.Especializacion.valueOf(especialidad!!).toString(), // Convierte la especialidad a enum
            rol = this.rol,
            rutaImagen = this.ruta_imagen ?: ""
        )
    }
}

fun Plantilla.toEntity(): PersonalEntity {
    if (rol == "jugador") {
        val jugador = this as Jugador

        return JugadorEntity(
            id = jugador.id,
            nombre = jugador.nombre,
            apellidos = jugador.apellidos,
            fechaNacimiento = jugador.fechaNacimiento,
            fechaIncorporacion = jugador.fechaIncorporacion,
            salario = jugador.salario,
            pais = jugador.pais,
            rol = jugador.rol,
            posicion = Jugador.Posicion.valueOf(jugador.posicion!!.toString()),
            dorsal = jugador.dorsal!!,
            altura = jugador.altura!!,
            peso = jugador.peso!!,
            goles = jugador.goles!!,
            partidosJugados = jugador.partidosJugados!!,
            rutaImagen = jugador.rutaImagen
        )
    } else {

        val entrenador = this as Entrenador
        return EntrenadorEntity(
            id = entrenador.id,
            nombre = entrenador.nombre,
            apellidos = entrenador.apellidos,
            fechaNacimiento = entrenador.fechaNacimiento,
            fechaIncorporacion = entrenador.fechaIncorporacion,
            salario = entrenador.salario,
            pais = entrenador.pais,
            rol = entrenador.rol,
            especialidad = Entrenador.Especializacion.valueOf(this.especialidad),
            rutaImagen = entrenador.rutaImagen
        )


    }
}
fun PersonalEntity.toJugador(): Jugador {
    val jugador = this as Jugador
    return Jugador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = fechaNacimiento,
        fechaIncorporacion = fechaIncorporacion,
        salario = this.salario,
        pais = this.pais,
        rol = this.rol,
        posicion = Jugador.Posicion.valueOf(posicion!!.toString()).toString(),
        dorsal = dorsal!!,
        altura = altura!!,
        peso = peso!!,
        goles = goles!!,
        partidosJugados = partidosJugados!!,
    )
}

fun PersonalEntity.toEntrenador(): Entrenador {
    val entrenador = this as Entrenador
    return Entrenador(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = fechaNacimiento,
        fechaIncorporacion = fechaIncorporacion,
        salario = this.salario,
        pais = pais,
        rol = this.rol,
        especialidad = Entrenador.Especializacion.valueOf(especialidad!!.toString()).toString(),
        rutaImagen = entrenador.rutaImagen
    )
}

fun PersonalEntity.toModel() : Plantilla {
    return if (rol == "jugador"){
        val jugador = this as Jugador
        Jugador(
            id = this.id,
            nombre = this.nombre,
            apellidos = this.apellidos,
            fechaNacimiento = this.fechaNacimiento,
            fechaIncorporacion = this.fechaIncorporacion,
            salario = this.salario,
            pais = this.pais,
            rol = this.rol,
            posicion = Jugador.Posicion.valueOf(posicion!!.toString()).toString(),
            dorsal = this.dorsal!!,
            altura = this.altura!!,
            peso = this.peso!!,
            goles = this.goles!!,
            partidosJugados = this.partidosJugados!!
        )
    } else {
        val entrenador = this as Entrenador
        Entrenador(
            id = this.id,
            nombre = this.nombre,
            apellidos = this.apellidos,
            fechaNacimiento = this.fechaNacimiento,
            fechaIncorporacion = this.fechaIncorporacion,
            salario = this.salario,
            pais = this.pais,
            rol = this.rol,
            especialidad = Entrenador.Especializacion.valueOf(especialidad!!.toString()).toString(),
            rutaImagen = entrenador.rutaImagen
        )

    }
}
//PARTE BUENA