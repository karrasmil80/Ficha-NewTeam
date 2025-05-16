package org.example.fichanewteam.plantilla.viewmodel

import com.github.michaelbull.result.*
import org.example.fichanewteam.plantilla.service.PlantillaService
import org.example.fichanewteam.plantilla.storage.PlantillaStorage
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.models.Plantilla
import org.example.fichanewteam.plantilla.models.Entrenador
import org.example.fichanewteam.plantilla.models.Jugador
import org.example.fichanewteam.plantilla.mapper.toModel
import org.example.fichanewteam.routes.RoutesManager
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.image.Image
import org.example.fichanewteam.plantilla.storage.PlantillaZipStorage
import org.lighthousegames.logging.logging
import java.io.File
import kotlin.String
import kotlin.collections.toMutableList


private val logger = logging()

class PlantillaViewModel(
    private val servicio: PlantillaService,
    private val storage: PlantillaStorage,
    private val storageZip: PlantillaZipStorage
) {
    val state: SimpleObjectProperty<ExpedienteState> = SimpleObjectProperty(ExpedienteState())

    init {
        loadPlantilla()
        loadTypes()
    }

    private fun loadTypes() {
        state.value = state.value.copy(typesPlantilla = TipoFiltro.entries.map { it.value })
    }

    private fun loadPlantilla() {
        servicio.findAll().onSuccess {
            state.value = state.value.copy(plantilla = it)
            updateActualState()
        }
    }

    private fun updateActualState() {
        //Consultas jugadores
        val golesPromedioConsulta = state.value.jugador.map{ it.goles }.average()
        val salarioMaximoConsulta = state.value.jugador.mapNotNull { it.salario }.maxOrNull() ?: 0.0
        val alturaMinimaConsulta = state.value.jugador.mapNotNull{ it.altura }.minOrNull() ?: 0.0
        val totalPartidosConsulta = state.value.jugador.sumOf{ it.partidosJugados }
        val minutospromedioConsulta = state.value.jugador.mapNotNull { it.minutosJugados }.average()

        //Consultas Entrenadores
        val salarioPromedioConsulta = state.value.entrenador.mapNotNull { it.salario }.average()
        val incorporacionAntiguaConsulta = state.value.entrenador.minBy { it.fechaIncorporacion }.toString()
        val nacimientoActualConsulta =  state.value.entrenador.maxBy { it.fechaNacimiento }.toString()
        val entrenadoresAsistentesConsulta = state.value.entrenador.map { it.especialidad == "ASISTENTE" }.count()
        val entrenadoresEspañolesConsulta = state.value.entrenador.map { it.pais == "España" }.count()


        state.value = state.value.copy(

            //State de jugadores
            golesPromedio = golesPromedioConsulta,
            salarioMaximo = salarioMaximoConsulta,
            alturaMinima = alturaMinimaConsulta,
            totalPartidos = totalPartidosConsulta,
            minutosPromedio = minutospromedioConsulta,

            //State de entrenadores
            salarioPromedio = salarioPromedioConsulta,
            incorporacionAntigua = incorporacionAntiguaConsulta,
            entrenadoresAsistentes = entrenadoresAsistentesConsulta,
            nacimientoActual = nacimientoActualConsulta,
            entrenadoresEspanoles =entrenadoresEspañolesConsulta ,

            //Aqui se guarda el state de los miembros de la plantilla
            miembro = PlantillaState()
        )
    }

    /* //EN PRINCIPIO YA LA TENGO YO HECHA EN EL CONTROLLER
    fun plantillaFilteredList(tipo: String, nombre: String): List<Plantilla>{
        return state.value.plantilla
            .filter { plantilla ->
                when (tipo){
                    TipoFiltro.JUGADOR.value -> true
                    TipoFiltro.ENTRENADOR.value -> true
                    else -> false
                }
            }.filter { plantilla ->
                plantilla.nombre.contains(nombre, true)
            }
    }

     */

    fun savePlantillaToJson(file:File): Result<Long, PlantillaError> {
        return storage.storageDataJson(file, state.value.plantilla)
    }

    fun loadPlantillaJson(file: File, withImages: Boolean = false): Result<List<Plantilla>, PlantillaError> {
        return storage.deleteAllImages().andThen {
            storage.loadDataJson(file).onSuccess {
                servicio.deleteAll()
                servicio.saveAll(
                    if (withImages)
                        it
                    else
                        it.map{ a -> a.copy(id = Plantilla.NEW_ID, rutaImagen = TipoImagen.SIN_IMAGEN.value) }
                )
                loadPlantilla()
            }
        }
    }

    fun updatePlantillaSelecionado(plantilla: Plantilla, jugador: Jugador, entrenador: Entrenador) {
        var imagen = Image(RoutesManager.getResourceAsStream("images/default_profile.png"))
        var fileImage = File(RoutesManager.getResource("images/default_profile.png").toURI())

        storage.loadImage(plantilla.rutaImagen).onSuccess {
            imagen = Image(it.toString())
            fileImage = it as File
        }

        when(plantilla.rol){
            "Jugador" -> state.value = state.value.copy(
                jugador = listOf(JugadorState(
                    id = jugador.id,
                    nombre = jugador.nombre,
                    apellidos = jugador.apellidos,
                    fechaNacimiento = jugador.fechaNacimiento,
                    fechaIncorporacion = jugador.fechaIncorporacion,
                    salario = jugador.salario,
                    pais = jugador.pais,
                    rol = jugador.rol,
                    posicion = jugador.posicion.toString(),
                    dorsal = jugador.dorsal,
                    altura = jugador.altura,
                    peso = jugador.peso,
                    goles = jugador.goles,
                    partidosJugados = jugador.partidosJugados,
                    minutosJugados = jugador.minutosJugados,
                ).toModel())

            )
            "Entrenador" -> state.value = state.value.copy(
                entrenador = listOf(EntrenadorState(
                    id = entrenador.id,
                    nombre = entrenador.nombre,
                    apellidos = entrenador.apellidos,
                    fechaNacimiento = entrenador.fechaNacimiento,
                    fechaIncorporacion = entrenador.fechaIncorporacion,
                    salario = entrenador.salario,
                    pais = entrenador.pais,
                    rol = entrenador.rol,
                    especialidad = entrenador.especialidad
                ).toModel())
            )
        }
    }

    fun eliminarJugador(): Result<Unit, PlantillaError> {
        val jugador = (state.value.jugador.find { it.id.toLong() == it.id.toLong() } ) as JugadorState
        val myId = jugador.id.toLong()

        jugador.fileImage.let { file ->
            if (file?.name != TipoImagen.SIN_IMAGEN.value) {
                storage.deleteImage(file.toString())
            }
        }

        servicio.deleteById(myId)
        state.value = state.value.copy(jugador = state.value.jugador.toMutableList().apply{ this.removeIf { it.id == myId } })

        updateActualState()
        return Ok(Unit)
    }

    fun eliminarEntrenador(): Result<Unit, PlantillaError> {
        val entrenador = (state.value.entrenador.find { it.id.toLong() == it.id.toLong() } ) as EntrenadorState
        val myId = entrenador.id.toLong()

        entrenador.fileImage.let { file ->
            if (file?.name != TipoImagen.SIN_IMAGEN.value) {
                storage.deleteImage(file.toString())
            }
        }

        servicio.deleteById(myId)
        state.value = state.value.copy(entrenador = state.value.entrenador.toMutableList().apply{ this.removeIf { it.id == myId } })

        updateActualState()
        return Ok(Unit)
    }

//    fun crearJugador(): Result<Jugador, PlantillaError> {
//        val newJugadorTemp = (Jugador.copy())
//    }
//
//   fun crearEntrenador(): Result<Entrenador, PlantillaError> {}
//
//    fun editarPlantilla(): Result<Plantilla, PlantillaError> {}
//
//    fun updateimagePlantillaOperacion(fileImage: File){}
//
    fun exportToZip(fileToZip: File): Result<Unit, PlantillaError> {
        servicio.findAll().andThen {
            storageZip.exportToZip(fileToZip, it)
        }.onFailure {
            return Err(it)
        }
        return Ok(Unit)
    }

    fun loadPlantillaFromZip(fileToUnzip: File): Result<List<Plantilla>, PlantillaError> {
        return storageZip.loadFromZip(fileToUnzip).onSuccess {lista ->
            servicio.deleteAll().andThen {
                servicio.saveAll(lista.map{ a -> a.copy(id = Plantilla.NEW_ID) })
            }.onFailure {
                loadPlantilla()
            }
        }
    }

//    fun changePlantillaOperacion(newValue: TipoOperacion){
//        if (newValue == TipoOperacion.EDITAR){
//            state.value = state.value.copy(
//                plantilla = state.value.plantilla.copy(),
//                tipoOperacion = newValue,
//            )
//        }else{
//            state.value = state.value.copy(
//                plantilla = PlantillaState(),
//                tipoOperacion = newValue,
//            )
//        }
//    }

    fun updateDataPlantilla() {}


    enum class TipoOperacion(val value: String){
        NUEVO("Nuevo"), EDITAR("Editar")
    }

    enum class TipoImagen(val value: String) {
        SIN_IMAGEN("images/default_profile.png"), EMPTY("sin-imagen.png")
    }

    enum class TipoFiltro(val value: String) {
        JUGADOR("Jugador: si"), ENTRENADOR("Entrenador: si")
    }

    data class ExpedienteState(
        //Contenedores
        val typesPlantilla: List<String> = emptyList(),
        val plantilla: List<Plantilla> = emptyList(),
        val jugador: List<Jugador> = emptyList(),
        val entrenador: List<Entrenador> = emptyList(),

        //Variables de las consultas de jugadores
        val golesPromedio: Int = 0,
        val salarioMaximo: Double = 0.00,
        val alturaMinima: Double = 0.00,
        val totalPartidos: Int = 0,
        val minutosPromedio: Double = 0.00,

        //Variables de las consultas de entrenador
        val salarioPromedio: Double = 0.00,
        val incorporacionAntigua: String = "",
        val nacimientoActual: String = "",
        val entrenadoresAsistentes: Int = 0,
        val entrenadoresEspanoles: Int = 0,

        //Miembro hace referencia al conjunto es decir el individual de plantilla
        val miembro: PlantillaState = PlantillaState(),

        val tipoOperacion: TipoOperacion = TipoOperacion.NUEVO
    )

    data class PlantillaState(
        val id: String = "",
        val nombre: String = "",
        val apellidos: String = "",
        val fechaNacimiento: String = "",
        val fechaIncorporacion: String = "",
        val salario: Double = 0.00,
        val pais: String = "",
        val rol: String = "",
        val rutaImagen: Image = Image(RoutesManager.getResourceAsStream("images/default_profile.png")),
        val fileImage: File? = null,
        val oldFileImage: File? = null,
    )

    data class JugadorState(
        val id: Long,
        val nombre: String,
        val apellidos: String,
        val fechaNacimiento: String,
        val fechaIncorporacion: String,
        val salario: Double?,
        val pais: String,
        val rol: String,
        val posicion: String,
        val dorsal: Int?,
        val altura: Double?,
        val peso: Double?,
        val goles: Int,
        val partidosJugados: Int,
        val rutaImagen: Image = Image(RoutesManager.getResourceAsStream("images/default_profile.png")),
        val minutosJugados: Double?,
        val fileImage: File? = null,
        val oldFileImage: File? = null,
    )

    data class EntrenadorState(
        val id: Long,
        val nombre: String,
        val apellidos: String,
        val fechaNacimiento: String,
        val fechaIncorporacion: String,
        val salario: Double?,
        val pais: String,
        val rol: String,
        val especialidad: String,
        val rutaImagen: Image = Image(RoutesManager.getResourceAsStream("images/default_profile.png")),
        val fileImage: File? = null,
        val oldFileImage: File? = null,
    )
}