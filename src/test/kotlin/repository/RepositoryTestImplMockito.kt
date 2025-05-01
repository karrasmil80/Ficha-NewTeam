package repository

import org.example.fichanewteam.plantilla.dao.PersonalEntity
import org.example.fichanewteam.plantilla.dao.PlantillaDao
import org.example.fichanewteam.plantilla.repositories.PlantillaRepositoryImpl
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.atLeastOnce
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(MockitoExtension::class)
class RepositoryTestImplMockito {

    @Mock
    private lateinit var dao: PlantillaDao

    @InjectMocks
    private lateinit var repository: PlantillaRepositoryImpl

    @Test
    @DisplayName("Debería devolver una lista con los miembros de la plantilla")
    fun findAllTest() {
        val miembro = PersonalEntity(
            id = 1L,
            nombre = "Pedro",
            apellidos = "Gonzalez",
            fechaNacimiento = "1982-04-04",
            fechaIncorporacion = "1990-04-04",
            salario = 1000.00,
            pais = "España",
            rol = "Entrenador",  // Asegúrate de que sea un "Entrenador"
        )

        whenever(dao.findAll()).thenReturn(listOf(miembro))

        val miembroLista = repository.findAll()

        assertAll(
            { assertNotNull(miembroLista.size == 1, ("Miembro no nulo")) },
            { assertEquals(1, miembroLista.size, "Debe haber 1 miembro") },
            { assertEquals("Pedro", miembroLista[0].nombre) },
            { assertEquals("Gonzalez", miembroLista[0].apellidos) },
            { assertEquals("1982-04-04", miembroLista[0].fechaNacimiento) },
            { assertEquals("1990-04-04", miembroLista[0].fechaIncorporacion) },
            { assertEquals(1000.0, miembroLista[0].salario) },
            { assertEquals("España", miembroLista[0].pais) }
        )
        verify(dao, atLeastOnce()).findAll()
    }
}

