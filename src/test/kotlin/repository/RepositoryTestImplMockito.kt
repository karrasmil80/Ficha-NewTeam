package repository


import org.example.fichanewteam.plantilla.dao.PersonalEntity
import org.example.fichanewteam.plantilla.dao.PlantillaDao
import org.example.fichanewteam.plantilla.repositories.PlantillaRepositoryImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import kotlin.test.Test
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
            rol = "Entrenador"
        )

        whenever(dao.findAll()).thenReturn(listOf(miembro))
        val miembroLista = repository.findAll()

        // Asserts individuales
        assertNotNull(miembroLista, "La lista de miembros no debería ser nula")
        assertEquals(1, miembroLista.size, "La lista debería contener exactamente un miembro")
        assertEquals("Pedro", miembroLista[0].nombre, "El nombre del miembro debería ser 'Pedro'")
        assertEquals("Gonzalez", miembroLista[0].apellidos, "Los apellidos del miembro deberían ser 'Gonzalez'")
        assertEquals("1982-04-04", miembroLista[0].fechaNacimiento, "La fecha de nacimiento no coincide")
        assertEquals("1990-04-04", miembroLista[0].fechaIncorporacion, "La fecha de incorporación no coincide")
        assertEquals(1000.0, miembroLista[0].salario, "El salario no coincide")
        assertEquals("España", miembroLista[0].pais, "El país no coincide")
        assertEquals("Entrenador", miembroLista[0].rol, "El rol no coincide")

        verify(dao, atLeastOnce()).findAll()
    }
}
