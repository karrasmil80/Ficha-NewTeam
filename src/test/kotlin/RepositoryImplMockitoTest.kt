package org.example.fichanewteam

import org.example.fichanewteam.plantilla.dao.PlantillaDao
import org.example.fichanewteam.plantilla.mapper.toEntity
import org.example.fichanewteam.plantilla.models.Entrenador
import org.example.fichanewteam.plantilla.repositories.PlantillaRepository
import org.example.fichanewteam.plantilla.repositories.PlantillaRepositoryImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class TestPrueba {

    @Mock
    private lateinit var dao: PlantillaDao

    @InjectMocks
    private lateinit var repository: PlantillaRepositoryImpl

    @Test
    @DisplayName("debe devolver una lista de toda la plantilla")
    fun findAll() {
        val entrenador = Entrenador(
            id = 1,
            nombre = "Jose Antonio",
            apellidos = "Lovato",
            fechaNacimiento = "1950-01-01",
            fechaIncorporacion = "1970-01-01",
            salario = 100.0,
            pais = "España",
            rol = "Entrenador",
            especialidad = Entrenador.Especializacion.ENTRENADOR_PORTEROS.toString(),
            rutaImagen = ""
        )

        // Simula lo que devuelve el DAO (simula entidad)
        val entrenadorEntity = entrenador.toEntity() // Asume que existe .toEntity()
        whenever(dao.findAll()).thenReturn(listOf(entrenadorEntity))

        // Ejecuta la función real del repositorio
        val result = repository.findAll()

        // Verifica que el resultado contiene el entrenador que esperabas
        assertEquals(1, result.size)
        assertTrue(result.first() is Entrenador)
        assertEquals("Jose Antonio", (result.first() as Entrenador).nombre)
    }
}