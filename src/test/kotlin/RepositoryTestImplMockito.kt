

import org.example.fichanewteam.plantilla.dao.PlantillaDao
import org.example.fichanewteam.plantilla.repositories.PlantillaRepositoryImpl
import org.example.models.Entrenador
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(MockitoExtension::class)
class RepositoryTestImplMockito {

    @Mock
    private lateinit var dao : PlantillaDao

    @InjectMocks
    private lateinit var repository : PlantillaRepositoryImpl

    @Nested
    @DisplayName("Casos correctos")
    inner class TrueCase {
        @Test
        @DisplayName("Deberia de devolver una lista de los miembros de la plantilla")
        fun findAll() {
            val miembro = Entrenador(
                id = 1L,
                nombre = "Pedro",
                apellidos = "Gonzalez",
                fechaNacimiento = "1982-04-04",
                fechaIncorporacion = "1990-04-04",
                salario = 1000.00,
                pais = "España",
                rol = "Jugador",
                especialidad = Entrenador.Especializacion.ENTRENADOR_PORTEROS.toString()
            )

            whenever(repository.findAll()).thenReturn(listOf(miembro))

            val miembroLista = repository.findAll()

            assertAll(
                { assertNotNull(miembroLista.size == 1, "Miembro no nulo") },
                { assertEquals(1, miembroLista.size, "Miembro correcto") },
                { assertEquals("Pedro", miembroLista[0].nombre, "Pedro") },
                { assertEquals("Gonzalez", miembroLista[0].apellidos, "Gonzalez") },
                { assertEquals("1982-04-04", miembroLista[0].fechaNacimiento, "1990-04-04") },
                { assertEquals("1990-04-04", miembroLista[0].fechaNacimiento, "1990-04") },
                { assertEquals(1000.0, miembroLista[0].salario, "Jugador") },
                { assertEquals("España", miembroLista[0].pais) }
            )
        }
    }
}